package me.weyye.todaynews.model;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class NewsDetail {

    /**
     * detail_source : 思远军事
     * media_user : {"no_display_pgc_icon":false,"avatar_url":"http://p1.pstatp.com/thumb/2b6000872cd3a880c79","id":"6091202516","screen_name":"思远军事"}
     * publish_time : 1493011498
     * title : 中国为何主动曝光这一大杀器？白宫官员表示压力巨大了
     * url : http://toutiao.com/group/6412435518591599106/
     * is_original : true
     * is_pgc_article : true
     * content : <div><p><img src="http://p1.pstatp.com/large/1c67000332373537f0ff" img_width="640" img_height="360" inline="0" alt="中国为何主动曝光这一大杀器？白宫官员表示压力巨大了" onerror="javascript:errorimg.call(this);"></p><p class="pgc-img-caption">图为中国鹰击62导弹发射画面</p><p>【思远军事第228期】近日，据美国洛杉矶时报发表文章称，中国已经在沿海地区大量部署了鹰击62反舰导弹，从中国官媒近期报道中公开的照片可以知道，鹰击62已经具备了远海作战能力，这一点让美国感到巨大压力了。有白宫官员表示，中国已经成为世界上装备反舰导弹种类最多的国家之一，形成了远、中、近多层海上反舰作战能力。</p><p>鹰击-62是中国航天科工集团下属单位研制的，该导弹的诞生时间较晚。据可靠资料显示，岸基型鹰击-62导弹系统直到2008年才正式装备部队。鹰击-62除了岸基型，还有舰载型，主要装备在052C型驱逐舰上，每艘舰装两座4联倾斜式发射箱；空射型主要装备了轰-6系列战役战术轰炸机，每架飞机可挂6枚。</p><p><img src="http://p3.pstatp.com/large/1c6e0000841ab42ca326" img_width="640" img_height="425" inline="0" alt="中国为何主动曝光这一大杀器？白宫官员表示压力巨大了" onerror="javascript:errorimg.call(this);"></p><p class="pgc-img-caption">图为中国鹰击62反舰导弹</p><p>【思远军事第228期】鹰击-62反舰导弹是中国在本世纪初推出的第一款远程的亚音速掠海飞行反舰巡航导弹。此前，鹰击多个系列的亚音速反舰导弹只有100多公里的有效射程，且它们都采用了小型涡喷发动机，这种动力系统的动力输出效率较低，耗油量很高，因此导致了导弹射程无法进一步提高。</p><p>不过，鹰击-62首次采用了技术成熟的小型涡扇发动机，其耗油量要比涡喷发动机小许多，再加上鹰击-62导弹弹体庞大，内部携带的燃油自然较多。因此，鹰击-62的有效射程达400-600千米，鹰击-62可以说是中国航天科工集团在反舰导弹设计历史上的一个里程碑。</p><p><img src="http://p1.pstatp.com/large/1c6d00008eebccce3e2f" img_width="550" img_height="375" inline="0" alt="中国为何主动曝光这一大杀器？白宫官员表示压力巨大了" onerror="javascript:errorimg.call(this);"></p><p class="pgc-img-caption">图为中国鹰击62导弹</p><p>【思远军事第228期】此外，由于中国官方打造的北斗全球卫星定位系统已经越来越完善，因此中国的精确制导武器可以基本上达到美军的水平，指哪打哪。之前的中远程反舰导弹在飞行中段，要么需要发射平台对导弹发出制导指令，要么提前在弹上计算机输入导弹的弹道轨迹路径，在末段依靠导弹自身的导引头来搜寻目标，鹰击-62的制导方式也有了一定变革。</p><p>由于北斗导航系统的辅助，发射平台可提前在弹上计算机输入目标数据，待鹰击-62反舰导弹飞到末段时，导弹上北斗信号接收器材还可根据目标的移动变量向导弹发出新的制导指令。当然，鹰击-62导弹主要还是依靠主动雷达导引头。据称，该导引头可搜索并锁定正面40千米范围内的海面目标。总之，陆基、海基和空基的鹰击-62反舰导弹已经构成了中国日益严密的反舰体系的重要一环。</p><p>喜欢“思远军事”的同学可以关注我们的微信公众号：siyuanjunshi</p></div>
     * source : 思远军事
     * video_play_count : 0
     */

    public String detail_source;
    /**
     * no_display_pgc_icon : false
     * avatar_url : http://p1.pstatp.com/thumb/2b6000872cd3a880c79
     * id : 6091202516
     * screen_name : 思远军事
     */

    public MediaUserBean media_user;
    public long publish_time;
    public String title;
    public String url;
    public boolean is_original;
    public boolean is_pgc_article;
    public String content;
    public String source;
    public int video_play_count;

    public static class MediaUserBean {
        public boolean no_display_pgc_icon;
        public String avatar_url;
        public String id;
        public String screen_name;
    }
}
