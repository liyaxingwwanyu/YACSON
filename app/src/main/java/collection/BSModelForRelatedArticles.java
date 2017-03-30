package collection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import login.BSModelForLogin;
import top.coolook.gather.Gather;
import top.coolook.gather.utils.AgentVoData;
import top.coolook.model.User;
import top.coolook.model.UserComment;
import top.coolook.vo.AgentUserComment;
import top.coolook.vo.AgentVo;
import top.coolook.vo.ConArticleContentVo;
import top.coolook.vo.ConArticleVo;
import util.CollectionUtil;
import util.DateTime;
import util.RegexUtil;


/**
 * Created by Administrator on 2016/6/14.
 */
public class BSModelForRelatedArticles extends BSModelForLogin {

    public static final String mIDs[] = {"RelatedArticles", BSModelForLogin.mIDs[0], "Comments", "Like", "collection", "Comments", "MyCollections", "Articles"};
    public static final String mStates[] = {"Load", "NoMore", "RemoveAll", "NoLogin", "CommentsNull", BSModelForLogin.mStates[5], BSModelForLogin.mStates[7], "UpdateInfo", "New", "Hot",
            "PrepareEdit", "PrepareCancel", "PrepareDelete", "More", "Null", "Delete","PseudocollectSuccess","ToCoolook"};//0-17

    public static final String SP_NAME = "CurArticleInfo";
    public static final String SP_FILED[] = {
            "ContentUrl", "ArticleID", "Title", "Attr", "ImageUrl", "ImagePath", "IsScreen", "IsGameReplaceToCType", "IsJump", "CommentsCount", "IsLike", "IsCollection", "Abstract", "MediaSource", "ImageUrls", "SourceUrl"//0-15
    };
    private SharedPreferences mSP;

    private List<String> mRelatedArticleKeys;
    // 0-ContentUrl,1-ArticleID, 2-Title, 3-IsH5, 4-ImageUrl, 5-ImagePath, 6-IsScreen, 7-CType, 8-IsJump, 9-CommentsCount, 10-IsLike, 11-IsCollection, 12-Abstract, 13-MediaSource, 14-ImageUrls, 15-SourceUrl
    private Map<String, List<String>> mRelatedArticles;
    private final int mLoadCount = 3;

    //0-用户头像url，1-用户名，2-评论，3-时间，4-是否显示删除
    private List<List<String>> mArticleComments;
    private List<String> mArticleCommentIDs;
    private int mDelCommentPos;

    private List<String> mOperationArticleKeys;
    private Map<String, List<String>> mHistoryInfo;

    private String mCommentContent;
    private int mCommentType = -1;

    public BSModelForRelatedArticles(Context aContext) {
        super(aContext);
        mRelatedArticleKeys = new ArrayList<String>();
        mRelatedArticles = new HashMap<String, List<String>>();
        mOperationArticleKeys = new ArrayList<String>();
        mHistoryInfo = new HashMap<String, List<String>>();
        mArticleComments = new ArrayList<List<String>>();
        mEditArticleID = new ArrayList<String>();
        mArticleCommentIDs = new ArrayList<String>();
        mSP = mContext.getSharedPreferences(BSModelForRelatedArticles.SP_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void init() {
    }

    public void initCurArticle(String[] aParams, Boolean aIsBack) {
        SharedPreferences.Editor lEditor = mSP.edit();
        lEditor.clear();
        if (aParams != null) {
            int lCount = BSModelForRelatedArticles.SP_FILED.length < aParams.length ? BSModelForRelatedArticles.SP_FILED.length : aParams.length;
            for (int i = 0; i < lCount; i++) {
                lEditor.putString(BSModelForRelatedArticles.SP_FILED[i], aParams[i]);
            }
        }
        lEditor.commit();
        String lOperationID = getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]);
        if (!aIsBack) {
            if (!TextUtils.isEmpty(lOperationID)) {
                mOperationArticleKeys.add(lOperationID);
                addHistory();
            }
        }
        mHandler.sendEmptyMessage(9932);
    }

    public void initRelatedArticles() {
        mHandler.sendEmptyMessage(9925);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRelatedArticleKeys.clear();
                mRelatedArticles.clear();
                mPos = 0;
                String lUrl = "http://cache.api.coolook.org/v2/content/article1-" + getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]) + "-" + Gather.getUUID(mContext) + ".json";

                String lUrl2 = "http://api-center.coolook.org/v2/content/getItemDetails-" + getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]) + ".json";
                // System.out.println("BSModelForRelatedArticles relatedArticles lUrl = " + lUrl);
                String lJsonData = mHttpRequestDefault.requestSync(lUrl);
                String lJsonData2 = mHttpRequestDefault.requestSync(lUrl2);
                //System.out.println("BSModelForRelatedArticles relatedArticles lJsonData = " + lJsonData);
                AgentVo lAgentVo = AgentVoData.resolve(lJsonData);
                if (lAgentVo != null) {
                    ConArticleContentVo lConArticleContentVo = lAgentVo.getConarticlecontentvo();
                    if (lConArticleContentVo != null) {
                        SharedPreferences.Editor lEditor = mSP.edit();
                        lEditor.putString(BSModelForRelatedArticles.SP_FILED[9], lConArticleContentVo.getCommentnum() + "");
                        lEditor.putString(BSModelForRelatedArticles.SP_FILED[10], (lConArticleContentVo.getIsmyliked() == 1) + "");
                        lEditor.putString(BSModelForRelatedArticles.SP_FILED[11], (lConArticleContentVo.getIsfavorited() == 1) + "");
                        lEditor.commit();
                        addHistory();
                        mHandler.sendEmptyMessage(9932);

                        List<ConArticleVo> lRelatedArticles = lConArticleContentVo.getList();
                        if (lRelatedArticles != null && lRelatedArticles.size() > 0) {
                            for (int i = 0; i < lRelatedArticles.size(); i++) {
                                ConArticleVo lRelatedArticle = lRelatedArticles.get(i);
                                String lArticleID = lRelatedArticle.getId() + "";
                                mRelatedArticleKeys.add(lArticleID);
                                String[] lArticleArr = {
                                        lRelatedArticle.getLinkurl(),
                                        lArticleID,
                                        lRelatedArticle.getTitle(),
                                        lRelatedArticle.getAttr() + "",
                                        TextUtils.isEmpty(lRelatedArticle.getTitlepic()) ? "" : lRelatedArticle.getTitlepic().split("\\|")[0],
                                        "",
                                        "",
                                        lRelatedArticle.getCtype() + "",
                                        "",
                                        lRelatedArticle.getCommentnum() + "",
                                        "",
                                        "",
                                        lRelatedArticle.getAbstracts(),
                                        lRelatedArticle.getPublication() != null ? lRelatedArticle.getPublication().getName() : "",
                                        lRelatedArticle.getTitlepic(),
                                        lRelatedArticle.getChannelid() == 6 ? lRelatedArticle.getSourceurl() : ""
                                };
                                if (lRelatedArticle.getChannelid() == 6) {
                                    //System.out.println("----->>>>> source url exist - title = " + lRelatedArticle.getTitle() + " ,Sourceurl = " + lRelatedArticle.getSourceurl());
                                }
                                mRelatedArticles.put(lArticleID, Arrays.asList(lArticleArr));
                            }
                        }
                    }
                }
                loadMoreRelated();
            }
        }).start();
        inputInfo(0, false);
    }

    public List<String> getRelatedArticle(int aPos) {  //
        if (aPos < mRelatedArticleKeys.size()) {
            return mRelatedArticles.get(mRelatedArticleKeys.get(aPos));
        }
        return null;
    }

    private void addHistory() {
        List<String> lInfo = new ArrayList<String>();
        for (int i = 0; i < BSModelForRelatedArticles.SP_FILED.length; i++) {
            lInfo.add(getSPForArticle(BSModelForRelatedArticles.SP_FILED[i]));
        }
        mHistoryInfo.put(getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]), lInfo);
    }

    public void loadMoreRelated() {
        if (mRelatedArticleKeys.size() > mPos) {
            mPos += mLoadCount;
            if (mPos >= mRelatedArticleKeys.size()) {
                mPos = mRelatedArticleKeys.size();
            }
            mHandler.sendEmptyMessage(9923);
        }
        if (mRelatedArticleKeys.size() <= mPos) {
            mHandler.sendEmptyMessage(9924);
        }
    }

    @Override
    public int getPosForReal() {//相关文章显示到哪一条
        return mPos;
    }

    @Override
    public int getPosForTotal() {
        return mRelatedArticleKeys.size();
    }

    @Override
    public void setCurPos(int aPosForTotal) {//点击第几条文章
        if (aPosForTotal < mRelatedArticleKeys.size()) {
            List<String> lArticle = mRelatedArticles.get(mRelatedArticleKeys.get(aPosForTotal));
//            if (aPosForTotal == 0)
//                RussiaUtil.burypoint(622, "文章1被点击");
//            else if (aPosForTotal == 1)
//                RussiaUtil.burypoint(623, "文章2被点击");
//            else if (aPosForTotal == 2)
//                RussiaUtil.burypoint(624, "文章3被点击");

            if (lArticle != null) {
                String[] lArticleArr = new String[lArticle.size()];
                lArticleArr = lArticle.toArray(lArticleArr);
                if (!TextUtils.isEmpty(lArticleArr[4])) {
                    File lFile = ImageLoader.getInstance().getDiskCache().get(lArticleArr[4]);
                    if (lFile != null) {
                        lArticle.set(5, lFile.getPath());
                        lArticleArr[5] = lFile.getPath();
                    }
                }
                initCurArticle(lArticleArr, false);
            }
        }
    }

    public boolean back() {
        int lOperationSize = mOperationArticleKeys.size();
        if (lOperationSize <= 1) {
            return false;
        }
        mOperationArticleKeys.remove(lOperationSize - 1);
        String lHistoryID = mOperationArticleKeys.get(lOperationSize - 2);
        List<String> lHistoryArticle = mHistoryInfo.get(lHistoryID);
        if (lHistoryArticle == null) {
            return false;
        }
        String[] lArticleArr = new String[lHistoryArticle.size()];
        lArticleArr = lHistoryArticle.toArray(lArticleArr);
        initCurArticle(lArticleArr, true);
        return true;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 9923://加载相关文章
                    mCurID = BSModelForRelatedArticles.mIDs[0];
                    mCurState = BSModelForRelatedArticles.mStates[0];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9924://没有更多文章
                    mCurID = BSModelForRelatedArticles.mIDs[0];
                    mCurState = BSModelForRelatedArticles.mStates[1];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9925://清空当前文章相关数据
                    mCurID = BSModelForRelatedArticles.mIDs[0];
                    mCurState = BSModelForRelatedArticles.mStates[2];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9926://评论成功
                    mCurID = BSModelForRelatedArticles.mIDs[2];
                    mCurState = BSModelForRelatedArticles.mStates[5];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9927://评论失败
                    mCurID = BSModelForRelatedArticles.mIDs[2];
                    mCurState = BSModelForRelatedArticles.mStates[6];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9928://点赞成功
                    mCurID = BSModelForRelatedArticles.mIDs[3];
                    mCurState = BSModelForRelatedArticles.mStates[5];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9929://点赞失败
                    mCurID = BSModelForRelatedArticles.mIDs[3];
                    mCurState = BSModelForRelatedArticles.mStates[6];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9930://收藏成功
                    mCurID = BSModelForRelatedArticles.mIDs[4];
                    mCurState = BSModelForRelatedArticles.mStates[5];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9931://收藏失败
                    mCurID = BSModelForRelatedArticles.mIDs[4];
                    mCurState = BSModelForRelatedArticles.mStates[6];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9932://更新点赞收藏
                    mCurID = BSModelForRelatedArticles.mIDs[0];
                    mCurState = BSModelForRelatedArticles.mStates[7];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9933://展示最新评论
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[8];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9934://展示最热评论
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[9];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9935://清空评论
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[2];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9936://加载收藏文章
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[0];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9937://删除我的收藏列表成功
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[5];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9938://删除我的收藏列表失败
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[6];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9939://继续加载更多收藏文章
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[13];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9940://继续加载更多评论
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[13];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9941://无收藏
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[14];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9942://删除评论
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[15];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
                case 9943://点击收藏伪更换收藏成功图
                    mCurID = BSModelForRelatedArticles.mIDs[5];
                    mCurState = BSModelForRelatedArticles.mStates[16];
                    updateObserver(BSModelForRelatedArticles.this);
                    break;
            }
        }
    };

    @Override
    public void inputInfo(String aCommentContent) {
        mCommentContent = "";
        if (TextUtils.isEmpty(super.getSP(BSModelForLogin.SP_FILED[1]))) {
            mCurID = BSModelForRelatedArticles.mIDs[1];
            mCurState = BSModelForRelatedArticles.mStates[3];
            updateObserver(BSModelForRelatedArticles.this);
            return;
        }
        if (TextUtils.isEmpty(aCommentContent)) {
            mCurID = BSModelForRelatedArticles.mIDs[1];
            mCurState = BSModelForRelatedArticles.mStates[4];
            updateObserver(this);
            return;
        }
        mCommentContent = aCommentContent;
    }

    //提交评论
    public void submitComment() {
        changeComment(0);
    }

    public void inputDelCommentPos(int aDelCommentPos) {
        mDelCommentPos = aDelCommentPos;
    }

    //删除评论
    public void deleteComment() {
        mCommentContent = "";
        changeComment(1);
    }

    public int getDelCommentPos() {
        return mDelCommentPos;
    }

    //更改评论。0-添加，1-删除
    private void changeComment(final int aType) {
        if (!TextUtils.isEmpty(getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]))) {
            if (aType == 0 && TextUtils.isEmpty(mCommentContent)) {
                return;
            }
            final int lDelCommentPos = mDelCommentPos;
            if (aType == 1 && lDelCommentPos >= mArticleCommentIDs.size()) {
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/interactive/comment";
                    Map<String, String> lCommentParam;
                    String lDelCommentID = "0";
                    if (aType == 1) {
                        lDelCommentID = mArticleCommentIDs.get(lDelCommentPos);
                    }
                    lCommentParam = getCommentParam(aType + "", lDelCommentID);
                    //System.out.println("BSModelForRelatedArticles changeComment lParam= " + lCommentParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lCommentParam);
                    // System.out.println("BSModelForRelatedArticles changeComment lJsonData=" + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                // 成功
                                SharedPreferences.Editor lEditor = mSP.edit();
                                lEditor.putString(BSModelForRelatedArticles.SP_FILED[9], mAgentVo.getCommentnum()+"");//从服务器获取 评论的总条数
                                lEditor.commit();
                                //System.out.println("BSModelForRelatedArticles.run=Commentnum=" + mAgentVo.getCommentnum()+"");

                                if (aType == 0) {
                                    inputInfo(0, false);
                                    mHandler.sendEmptyMessage(9926);
                                }
                                if (aType == 1) {
                                    int lCurDelPos = mArticleCommentIDs.indexOf(lDelCommentID);
                                    if (lCurDelPos >= 0) {
                                        mArticleCommentIDs.remove(lCurDelPos);
                                        mArticleComments.remove(lCurDelPos);
                                        mDelCommentPos = lCurDelPos;
                                        mHandler.sendEmptyMessage(9942);
                                    }
                                }
                                break;
                            case 8:
                                //id不存在
                                if (aType == 0) {
                                    mHandler.sendEmptyMessage(9927);
                                }
                                break;
                            case 9:
                                //必填数据为空
                                if (aType == 0) {
                                    mHandler.sendEmptyMessage(9927);
                                }
                                break;
                        }
                    } else {
                        //失败
                        if (aType == 0) {
                            mHandler.sendEmptyMessage(9927);
                        }
                    }
                }
            }).start();
        }
    }

    //aType: 0-添加，1-删除
    //aCommentID : 添加时为0,删除时为评论id
    private Map<String, String> getCommentParam(String aType, String aCommentID) {
//        CountryRelevantModel lCountryRelevantModel = new CountryRelevantModel(mContext);
//        String lCountryCode = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][1]);
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("uuid",super.getSP(BSModelForLogin.SP_FILED[18]));
        lParam.put("access_token", super.getSP(BSModelForLogin.SP_FILED[0]));
        lParam.put("id", aCommentID);// 	评论id（添加时为0,删除时为评论id）
        lParam.put("istatus", aType);//0:添加，1:删除
        lParam.put("artid", getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]));//文章id
        lParam.put("content", mCommentContent);//评论内容
        lParam.put("pid", "0");// 	父评论id 目前一级评论传0
        lParam.put("countrycode", "36");//当前code
        return lParam;
    }

    public void like() {
        likeCollection("1");
    }

    public void collection() {
        //System.out.println("BSModelForRelatedArticles.collection=="+getSPForArticle(BSModelForRelatedArticles.SP_FILED[11]));
//        if (!"true".equals(getSPForArticle(BSModelForRelatedArticles.SP_FILED[11]))) {
            likeCollection("4");
            //System.out.println("BSModelForRelatedArticles.collection  ===false ");
//        }
    }

    private void likeCollection(final String aType) {
        if (TextUtils.isEmpty(super.getSP(BSModelForLogin.SP_FILED[1]))) {
            mCurID = BSModelForRelatedArticles.mIDs[1];
            mCurState = BSModelForRelatedArticles.mStates[3];
            updateObserver(BSModelForRelatedArticles.this);
            return;
        }
        if (!TextUtils.isEmpty(getSPForArticle(BSModelForRelatedArticles.SP_FILED[1])) && ("1".equals(aType) || "4".equals(aType))) {
            if ("4".equals(aType)){
                mHandler.sendEmptyMessage(9943);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/interactive/like";
                    Map<String, String> lUploadHeadParam = getLikeCollectionParam(aType);
                    //System.out.println("BSModelForRelatedArticles like lParam=" + lUploadHeadParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lUploadHeadParam);
                    //System.out.println("BSModelForRelatedArticles like lJsonData= " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                // 成功
                                if ("1".equals(lUploadHeadParam.get("ltype"))) {
                                    mSP.edit().putString(BSModelForRelatedArticles.SP_FILED[10], ("0".equals(lUploadHeadParam.get("istatus"))) + "").commit();
                                    addHistory();
                                    mHandler.sendEmptyMessage(9928);
                                } else {
                                    mSP.edit().putString(BSModelForRelatedArticles.SP_FILED[11], ("0".equals(lUploadHeadParam.get("istatus"))) + "").commit();
                                    addHistory();
                                    mHandler.sendEmptyMessage(9930);
                                }
                                break;
                            case 2:
                                //评论点赞时文章id为0
                                if ("1".equals(lUploadHeadParam.get("ltype"))) {
                                    mHandler.sendEmptyMessage(9929);
                                } else {
                                    mHandler.sendEmptyMessage(9931);
                                }
                                break;
                            case 9:
                                //参数非法
                                if ("1".equals(lUploadHeadParam.get("ltype"))) {
                                    mHandler.sendEmptyMessage(9929);
                                } else {
                                    mHandler.sendEmptyMessage(9931);
                                }
                                break;
                        }
                    } else {
                        //失败
                        if ("1".equals(lUploadHeadParam.get("ltype"))) {
                            mHandler.sendEmptyMessage(9929);
                        } else {
                            mHandler.sendEmptyMessage(9931);
                        }
                    }
                }
            }).start();
        }
    }

    //aType:  1-点赞,4-收藏
    private Map<String, String> getLikeCollectionParam(String aType) {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("access_token", super.getSP(BSModelForLogin.SP_FILED[0]));
        lParam.put("objid", getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]));
        lParam.put("ltype", aType);//对象类型1:art（文章）,2:comment（评论）,3:prediction（竞猜）,4:favorite（收藏）
        lParam.put("uuid", Gather.getUUID(mContext));
        if ("1".equals(aType)) {
            String lLikeType = "true".equals(getSPForArticle(BSModelForRelatedArticles.SP_FILED[10])) ? "-1" : "0";
            //System.out.println("BSModelForRelatedArticles.=lLikeType="+lLikeType);
            lParam.put("istatus", lLikeType);// 	状态0:添加 -1:删除
        }
        if ("4".equals(aType)) {
            String lCollectionType = "true".equals(getSPForArticle(BSModelForRelatedArticles.SP_FILED[11])) ? "-1" : "0";
            lParam.put("istatus", lCollectionType);// 	状态0:添加 -1:删除
            //System.out.println("BSModelForRelatedArticles.=lCollectionType="+lCollectionType);
        }
        lParam.put("artid", "0");//如果为评论点赞，该值为文章id，如果其他类型传0
        return lParam;
    }

    //0:最新 1.最热
    public void inputInfo(int aCommentType, boolean aCheckPage) {
        if (aCheckPage && mCommentType == aCommentType) {
            return;
        }
        mCommentType = aCommentType;
        commentList(true);
    }

    //评论列表
    private int mCommentPage = 0;
    private int mCommentPageLoadState = -1;

    public void commentList(boolean aIsNew) {
        if (mCommentType == 0 || mCommentType == 1) {
            if (aIsNew) {
                mCommentPage = 0;
                mCommentPageLoadState = -1;
            } else {
                if (mCommentPageLoadState != mCommentPage) {
                    return;
                }
                mCommentPage++;
            }
            final int lPage = mCommentPage;//从第几页开始
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (lPage == 0) {
                        mArticleComments.clear();
                        mArticleCommentIDs.clear();
                        mHandler.sendEmptyMessage(9935);
                    }
                    String lType = mCommentType + "";//0:最新 1.最热
                    String lSize = "10";//每页多少条
                    String lUrl = "http://api-center.coolook.org/v3/commentlist-" + getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]) + "-" + Gather.getUUID(mContext) + "-" + lType + "-" + lPage + "-" + lSize + ".json";
                    // System.out.println("BSModelForRelatedArticles commentList lUrl = " + lUrl);
                    String lJsonData = mHttpRequestDefault.requestSync(lUrl);
                    // System.out.println("BSModelForRelatedArticles commentList lJsonData = " + lJsonData);
                    AgentVo lAgentVo = AgentVoData.resolve(lJsonData);
                    if (lAgentVo != null) {
                        switch (lAgentVo.getStatus()) {
                            case 0:
                                //成功
                                List<AgentUserComment> lAgentUserComments = lAgentVo.getUsercomment();
                                if (lAgentUserComments != null) {
                                    List<List<String>> lArticleComments = new ArrayList<List<String>>();
                                    List<String> lArticleCommentIDs = new ArrayList<String>();
                                    for (int i = 0; i < lAgentUserComments.size(); i++) {
                                        AgentUserComment lAgentUserComment = lAgentUserComments.get(i);
                                        User lUser = lAgentUserComment.getUser();
                                        UserComment lUserComment = lAgentUserComment.getComment();
                                        if (lUser != null && lUserComment != null) {
                                            String[] lArticleComment = {
                                                    lUser.getHeadurl(),
                                                    TextUtils.isEmpty(lUser.getNicename()) ? RegexUtil.getStartSubString(lUser.getEmail(), "@", false) : lUser.getNicename(),
                                                    lUserComment.getContent(),
                                                    DateTime.getDateTime(lUserComment.getCreatetime()),
//                                                    (!TextUtils.isEmpty(lUser.getEmail()) && lUser.getEmail().equals(getSP(BSModelForLogin.SP_FILED[9]))) + ""
                                                    (!TextUtils.isEmpty(lUser.getUuid()) && lUser.getUuid().equals(getSP(BSModelForLogin.SP_FILED[18]))) + "",
                                            };
                                            lArticleComments.add(Arrays.asList(lArticleComment));
                                            lArticleCommentIDs.add(lUserComment.getId());
                                        }
                                    }
                                    boolean lAddToNew = "0".equals(lType) && mCommentType == 0;
                                    boolean lAddToHot = "1".equals(lType) && mCommentType == 1;
                                    if (lAddToNew || lAddToHot) {
                                        mArticleComments.addAll(lArticleComments);
                                        mArticleCommentIDs.addAll(lArticleCommentIDs);
                                        if (lPage == 0) {
                                            mHandler.sendEmptyMessage(lAddToNew ? 9933 : 9934);
                                        } else {
                                            mHandler.sendEmptyMessage(9940);
                                        }
                                        mCommentPageLoadState = lAgentUserComments.size() <= 0 ? -1 : lPage;
                                    }
                                }
                                break;
                            case -1://没有信息（服务器错误）
                                if ("0".equals(lType) && mCommentType == 0 || "1".equals(lType) && mCommentType == 1) {
                                    mCommentPageLoadState = -1;
                                }
                                break;
                        }
                    } else {
                        //获取评论失败
                        if (("0".equals(lType) && mCommentType == 0) || ("1".equals(lType) && mCommentType == 1)) {
                            mCommentPage = lPage - 1;
                            mCommentPageLoadState = mCommentPage;
                        }
                    }
                }
            }).start();
        }
    }


    //收藏列表
    private int mCollectionPage = 0;
    private int mCollectionPageLoadState = -1;

    public void collectionList(boolean aIsNew) {
        if (aIsNew) {
            mCollectionPage = 0;
        } else {
            if (mCollectionPageLoadState != mCollectionPage) {
                return;
            }
            mCollectionPage++;
        }
        final int lPage = mCollectionPage;//从第几页开始
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (lPage == 0) {
                    mRelatedArticleKeys.clear();
                    mRelatedArticles.clear();
                }
                checkToken();
                String lSize = "10";//每页多少条
                String lAccessToken = getSP(BSModelForLogin.SP_FILED[0]);
                String lUrl = "https://api-center.coolook.org/interactive/favoritelist-" + Gather.getUUID(mContext) + "-" + lPage + "-" + lSize + ".json?access_token=" + lAccessToken;
                //System.out.println("BSModelForRelatedArticles collectionList lUrl = " + lUrl);
                String lJsonData = mHttpRequestDefault.requestSync(lUrl);
                //System.out.println("BSModelForRelatedArticles collectionList JsonData = " + lJsonData);
                AgentVo lAgentVo = AgentVoData.resolve(lJsonData);
                if (lAgentVo != null) {
                    switch (lAgentVo.getStatus()) {
                        case 0://成功
                            List<ConArticleVo> lArticles = lAgentVo.getArticles();
                            if (lArticles != null && lArticles.size() > 0) {
                                for (int i = 0; i < lArticles.size(); i++) {
                                    ConArticleVo lArticle = lArticles.get(i);
                                    String lArticleID = lArticle.getId() + "";
                                    mRelatedArticleKeys.add(lArticleID);
                                    String[] lArticleArr = {
                                            lArticle.getLinkurl(),
                                            lArticleID,
                                            lArticle.getTitle(),
                                            lArticle.getAttr() + "",
                                            TextUtils.isEmpty(lArticle.getTitlepic()) ? "" : lArticle.getTitlepic().split("\\|")[0],
                                            "",
                                            "",
                                            lArticle.getCtype() + "",
                                            "",
                                            lArticle.getCommentnum() + "",
                                            "",
                                            "",
                                            lArticle.getAbstracts(),
                                            lArticle.getPublication() != null ? lArticle.getPublication().getName() : "",
                                            lArticle.getTitlepic(),
                                            lArticle.getChannelid() == 6 ? lArticle.getSourceurl() : ""
                                    };
                                    if (lArticle.getChannelid() == 6) {
                                        //System.out.println("----->>>>> source url exist - title = " + lArticle.getTitle() + " ,Sourceurl = " + lArticle.getSourceurl());
                                    }
                                    mRelatedArticles.put(lArticleID, Arrays.asList(lArticleArr));
                                }
                                if (lPage == 0) {
                                    mHandler.sendEmptyMessage(9936);
                                } else {
                                    mHandler.sendEmptyMessage(9939);
                                }
                                mCollectionPageLoadState = lPage;
                            }
                            break;
                        case -1:
                            //获取收藏列表失败
                            mCollectionPageLoadState = -1;
                            break;
                    }
                } else {
                    //获取收藏列表失败
                }
                if (mRelatedArticleKeys.size() <= 0) {
                    mHandler.sendEmptyMessage(9941);
                }
            }
        }).start();
    }

    public List<List<String>> getArticleComments() {
        return mArticleComments;
    }

    private int mCollectionState; // 0：初始， 1：编辑
    private List<String> mEditArticleID;

    //标题栏编辑
    public void setCollectionState() {
        switch (mCollectionState) {
            case 0:
                mCollectionState = 1;
                mCurID = BSModelForRelatedArticles.mIDs[6];
                mCurState = BSModelForRelatedArticles.mStates[11];
                updateObserver(BSModelForRelatedArticles.this);
                break;
            case 1:
                if (mEditArticleID.size() <= 0) {
                    mCollectionState = 0;
                    mCurID = BSModelForRelatedArticles.mIDs[6];
                    mCurState = BSModelForRelatedArticles.mStates[10];
                    updateObserver(BSModelForRelatedArticles.this);
                }
                if (mEditArticleID.size() > 0) {
                    //删除
                    deleteCollection();
                }
                break;
        }
    }

    public boolean isCollectionEdit() {
        return mCollectionState == 1;
    }

    public boolean setCurPosForCollections(int aPos) {
        setCurPos(aPos);
        String lArticleID = getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]);
        if (mEditArticleID.contains(lArticleID)) {
            mEditArticleID.remove(lArticleID);
            if (mEditArticleID.size() == 0) {
                mCurID = BSModelForRelatedArticles.mIDs[6];
                mCurState = BSModelForRelatedArticles.mStates[11];
                updateObserver(BSModelForRelatedArticles.this);
            }
            return false;
        }
        mEditArticleID.add(lArticleID);
        if (mEditArticleID.size() == 1) {
            mCurID = BSModelForRelatedArticles.mIDs[6];
            mCurState = BSModelForRelatedArticles.mStates[12];
            updateObserver(BSModelForRelatedArticles.this);
        }
        return true;
    }

    public void setCurPosForSingleCollection(int aPos) {
        setCurPos(aPos);
        mEditArticleID.clear();
        String lArticleID = getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]);
        mEditArticleID.add(lArticleID);
        deleteCollection();
    }

    private void deleteCollection() {
        if (!TextUtils.isEmpty(getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]))) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/interactive/like";
                    Map<String, String> lDeleteCollectionParam = getDeleteCollectionParam();
                    // System.out.println("BSModelForRelatedArticles like lParam=" + lDeleteCollectionParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lDeleteCollectionParam);
                    //System.out.println("BSModelForRelatedArticles like lJsonData= " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                //成功
                                List<String> lEditArticleIDs = CollectionUtil.stringToListOfComma(lDeleteCollectionParam.get("objid"));
                                for (int i = 0; i < lEditArticleIDs.size(); i++) {
                                    mRelatedArticleKeys.remove(lEditArticleIDs.get(i));
                                }
//                                mRelatedArticleKeys.clear();
//                                mRelatedArticleKeys.addAll(mRelatedArticles.keySet());
                                mEditArticleID.clear();
                                mHandler.sendEmptyMessage(9937);
                                break;
                            case 2:
                                //评论点赞时文章id为0
                                mHandler.sendEmptyMessage(9938);
                                break;
                            case 9:
                                //参数非法
                                mHandler.sendEmptyMessage(9938);
                        }
                    } else {
                        //失败
                        mHandler.sendEmptyMessage(9938);
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getDeleteCollectionParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("access_token", super.getSP(BSModelForLogin.SP_FILED[0]));
        lParam.put("objid", CollectionUtil.listToStringOfComma(mEditArticleID));
        lParam.put("ltype", "4");//对象类型1:art（文章）,2:comment（评论）,3:prediction（竞猜）,4:favorite（收藏）
        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("istatus", "-1");// 	状态0:添加 -1:删除
        lParam.put("artid", "0");//如果为评论点赞，该值为文章id，如果其他类型传0
        return lParam;
    }

    public void loadSourceArticle() {
        mCurID = BSModelForRelatedArticles.mIDs[7];
        mCurState = BSModelForRelatedArticles.mStates[0];
        updateObserver(BSModelForRelatedArticles.this);
    }

    public void stateTitleCheckout(String stateCheckout) {
        mCurID = BSModelForRelatedArticles.mIDs[7];
        if (stateCheckout.equals(BSModelForMiniSite.BROWSE_SELECT_TYPES[1])) {
            mCurState = BSModelForRelatedArticles.mStates[1];
        } else {
            mCurState = BSModelForRelatedArticles.mStates[2];
        }

        updateObserver(BSModelForRelatedArticles.this);
    }

    public void showCoolookArticle() {
        mCurID = mIDs[7];
        mCurState = mStates[17];
        updateObserver(this);
    }

    private int mItemViewHeight, mComment;
    public void setItemViewHeight(int aItemViewHeight) {
        mItemViewHeight = aItemViewHeight;
    }

    public int getItemViewHeight() {
//        //System.out.println("BSModelForRelatedArticles.getItemViewHeight=="+mItemViewHeight);
        return mItemViewHeight;
    }
    public void setIsComment(int aComment) {
        mComment = aComment;
    }

    public int getIsComment() {
        return mComment;
    }

    public String getCurID() {
        return mCurID;
    }

    public String getCurState() {
        return mCurState;
    }

    public String getSPForArticle(String aKey) {
        return mSP.getString(aKey, "");
    }

    public void clearOperationArticle() {
        mOperationArticleKeys.clear();
    }
}
