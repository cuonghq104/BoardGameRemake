package techkids.cuong.myapplication.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cuong on 1/5/2017.
 */
public class BoardGame {

    public static String BOARD_GAME = "boardgame";

    private String name;

    private String imageUrl;

    private String detailUrl;

    private String rulesUrl;

    private String thumbUrl;

    private int minPlayer;

    private int maxPlayer;

    private String favoritePlayer;

    private int playingTime;

    private String[] categories;

    private String[] playType;

    private Paragraph[] tutorialBlocks;

    public BoardGame(String name, String imageUrl, String detailUrl, String rulesUrl, String thumbUrl, int minPlayer, int maxPlayer, String favoritePlayer, int playingTime, String[] categories, String[] playType, Paragraph[] tutorialBlocks) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.detailUrl = detailUrl;
        this.rulesUrl = rulesUrl;
        this.thumbUrl = thumbUrl;
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.favoritePlayer = favoritePlayer;
        this.playingTime = playingTime;
        this.categories = categories;
        this.tutorialBlocks = tutorialBlocks;
        this.playType = playType;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public String getFavoritePlayer() {
        return favoritePlayer;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    //    "https://cf.geekdo-images.com/images/pic2016054_md.jpg",
//            "https://view.publitas.com/p222-11815/coup/page/1",
    public static BoardGame[] boardGameArray = {
            new BoardGame("Werewolf basic",
                    "http://www.spielbude.ch/platform/apps/shop/images/obj-100839-4904-original.jpg",
                    "https://view.publitas.com/31715/238002/pdfs/29e027e72495889a166168ef7381e724e457f61b.pdf",
                    "https://view.publitas.com/31715/238026/pdfs/9a62cfa203a163ccae30cc9a02ca872e2321c5d6.pdf",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2015/05/09/10/44/1431161080_935.jpg",
                    8, 18,
                    "11-15",
                    30,
                    new String[] {"buffing", "deduction", "horror", "murder", "mystery", "party"},
                    new String[] {"partnership", "player elimination", "role playing", "voting"},
                    new Paragraph[]  {
                            Paragraph.createText("Trình tự game", "1. Giai đoạn ban đêm: Mọi người nhắm mắt, Quản Trò gọi vai trò đặc biệt nào thì vai trò ấy mở mắt và thực hiện chức năng của mình trong-yên-lặng. Trình tự gọi của Quản Trò như sau: Ăn trộm (Chỉ đêm đầu tiên)->Cupid (Chỉ đêm đầu tiên)->2 người yêu nhau (Chỉ đêm đầu tiên)->Bảo vệ->Sói->Tiên Tri->Phù Thủy->Thổi sáo->Những người bị thôi miên->Già Làng (Chỉ đêm đầu tiên)->Thợ Săn (Chỉ đêm đầu tiên)\n" +
                                            "2. Ban ngày: Quản trò ra hiệu mọi người mở mắt, thông báo những ai đã chết đêm qua. Sau đó, dân làng bình bầu treo cổ một người bị nghi ngờ là Ma Sói trong ban ngày (Có thể hoãn không treo). Nếu có 2 người cùng có số phiếu bầu treo như nhau thì không ai bị treo cả.",
                                    new String[] {"ban ngày", "ban đêm"}),
                            Paragraph.createCombine("Tiên tri", "Mỗi đêm, khi được gọi dậy Tiên Tri sẽ chỉ một người. Nếu người đó là Sói Quản trò sẽ gật đầu.\n" , "http://2.bp.blogspot.com/_0Pz0L1XQR1k/TB-cjdrXS2I/AAAAAAAAAUw/PHjf91XHa9U/s320/fortune+teller.png",
                                    new String[] {"sói", "mỗi đêm"}),
                            Paragraph.createCombine("Thợ săn", "Khi thợ săn chết, dù là dưới bất kỳ hình thức nào cũng có thể chọn một người chơi khác và kéo hắn xuống “Tuyền đường” cùng Thợ Săn", "http://1.bp.blogspot.com/-n_Ol3Hw7OTY/VGxC-hcsFYI/AAAAAAAABXI/bU3urwBQKK8/s1600/hunter.png",
                                    new String[] {"chết", "mỗi đêm", "giết"}),
                            Paragraph.createCombine("Phù thủy", "Phù Thủy có hai bình thuốc: Một bình dùng để cứu một người, còn một bình dùng để giết một người. Mỗi đêm, Quản trò khi gọi Phù Thủy dậy sẽ cho Phù Thủy biết người bị giết bởi Sói đêm đó, và Phù Thủy được quyền quyết định xem có cứu người ấy hay không. Sau đó, quản trò sẽ hỏi xem Phù Thủy có dùng bình giết giết ai hay không. Một khi đã dùng bình thì Phù Thủy sẽ mất đi chức năng tương ứng, tuy nhiên vẫn được gọi dậy mỗi đêm và biết ai chết. Lưu ý: Nếu đêm đó Bảo Vệ đã Bảo Vệ đúng người thì Quản trò sẽ lắc tay, ra dấu là không ai chết cả.", "http://2.bp.blogspot.com/_0Pz0L1XQR1k/TB-d0OJIX_I/AAAAAAAAAU4/JNek6CgDWPM/s320/witch.png",
                                    new String[] {"giết", "cứu"}),
                            Paragraph.createCombine("Cupid", "Đầu mỗi ván chơi, Cupid sẽ được gọi dậy và chọn ra hai người yêu nhau. Cupid sau đó nhắm mắt lại và hai người yêu nhau sẽ được Quản Trò gọi dậy để biết mặt và Vai Trò của nhau. Nếu hai người thuộc hai phe khác nhau (Sói vs Dân) thì họ thành phe thứ ba với nhiệm vụ là hai người cuối cùng sống sót.", "http://4.bp.blogspot.com/_hqAyb8K3zT8/SJxPWu9T55I/AAAAAAAAA7c/lnxMUs99j-g/s320/cupid.jpg",
                                    new String[] {"đêm đầu tiên", "sói", "dân"}),
                            Paragraph.createCombine("Ma Sói", "Mỗi đêm thức dậy, các Sói sẽ biết mặt lẫn nhau và sẽ cùng thống nhất giết một người. Sói có quyền không giết, và cũng có quyền tự giết lẫn nhau.", "http://i245.photobucket.com/albums/gg61/reakjean/Linh%20Tinh/WereWolf.jpg",
                                    new String[] {"mỗi đêm", "giết", "tiên tri"}),
                            Paragraph.createCombine("Già làng", "Già Làng có hai mạng khi bị cắn. Tuy nhiên, nếu bị treo cổ, bị Thợ Săn bắn hoặc bị Phù Thủy giết thì Già Làng vẫn chết ngay lập tức. Khi Già Làng chết, tất cả các vai trò đặc biệt trừ Thợ Săn đều bị mất chức năng.", "https://media3.scdn.vn/img1/2015/12_11/bai-ma-soi-characters-1m4G3-c01a0f.jpg",
                                    new String[] {"cắn", "treo cổ", "thợ săn", "phù thủy", "chết"}),
                            Paragraph.createCombine("Dân làng", "Năng lực rất đơn giản: Chết khi bị giết. Ngoài ra còn có năng lực bỏ phiếu treo người khác", "http://hstatic.net/936/1000019936/10/2015/7-28/danlang.jpg",
                                    new String[] {"chết, giết, bỏ phiếu"}),
                            Paragraph.createText("Luật chơi", "- Cách chơi của trò chơi là mỗi người sẽ được nhận lần lượt 1 lá bài để biết chức năng của mình, sau đó sẽ được quản trò bảo đi ngủ trong từng đêm. Mỗi đêm, quản trò gọi từng chức năng đặc biệt thức dậy như sói, cupid, witch ... để thực hiện công việc của mình rồi tiếp tục đi ngủ. Sau khi gọi lên hết cách chức năng. Quản trò thông báo mọi người mở mắt dậy và sẽ cho biết đêm qua ai bị giết.\n" +
                                            "\n" +
                                            "Sáng đó, mọi người sẽ cùng ngồi thảo luận với nhau, và bằng cơ sở lý luận, lập luận hay troll nhau gì gì đó ... mà cùng nhau tìm ra và treo cổ những con sói tinh ma.\n" +
                                            "\n" +
                                            "Còn về phía phe sói, thì cùng bảo vệ nhau để ly gián dân làng giành được chiến thắng cuối cùng.\n" +
                                            "\n" +
                                            "- Nếu một người bị giết hay loại khỏi trò chơi vì quy phạm nội quy game, quản trò sẽ không cho biết chức năng của họ cho đến khi kết thúc game.\n" +
                                            "\n" +
                                            "- Thứ tự gọi mỗi nhân vật trong đêm, phải theo 1 thứ tự nhất định không được thay đổi. Nếu quản trò gọi thứ tự sai, đôi khi sẽ gây sự nhầm lẫn hoặc sai sót trong game.\n" +
                                            "\n" +
                                            "- Người chơi đã bị loại khỏi game do bị giết hoặc vi phạm, phải tuyệt đối tuân thủ và tôn trọng những người chơi khác bằng cách giữ im lặng và theo dõi game tiếp tục diễn ra.\n" +
                                            "\n" +
                                            "- Mỗi buổi sáng, dân làng có tối đa 2 phút để cùng thảo luận với nhau.\n" +
                                            "\n" +
                                            "- Sau đó có 5 giây để chỉ tay về phía người mình tình nghi, nếu ai bị chỉ tay nhiều nhất sẽ có 1 phút để tự bào chữa cho mình.\n" +
                                            "\n" +
                                            "- Trong quá trình 1 phút tự bào chữa của người bị tình nghi, những người còn lại không được phép lên tiếng, nếu lên tiếng sẽ bị loại khỏi game. Trường hợp lên tiếng khi được người tình nghi hỏi trong lúc đang bào chữa cho mình, người lên tiếng sẽ bị mất quyền bỏ phiếu. Nếu nói chuyện ngoài luồng và cãi nhau, người chơi bị loại khỏi game.\n" +
                                            "- Hết 1 phút, những người có quyền bỏ phiếu sẽ biểu quyết xem có chấp nhận phần tự bào chữa của người bị tình nghi không. Nếu số phiếu chấp nhận nhiều hơn số phiếu không chấp nhận thì người đó được sống và tiếp tục trò chơi, ngược lại thì người đó sẽ chết (quản trò sẽ thu lại lá bài chức năng)\n" +
                                            "\n" +
                                            "- Dù người chơi bị chết giữ chức năng quan trọng nào đó, quản trò vẫn không được tiết lộ nên mỗi đêm vẫn phải gọi hết tất cả các chức năng của trò chơi lên để đánh lạc hướng người chơi và làm cơ sở cho phe sói có thời gian giả dạng đánh lừa dân làng. Do đó, việc bạn có chức năng đặc biệt nào đó mà bạn bị giết rồi mà quản trò vẫn gọi chức năng đó là điều hoàn toàn bình thường, đừng tỏ ra thắc mắc mà hãy im lặng theo dõi đến khi kết thúc trò chơi.",
                                    new String[] {})
                    }),
            new BoardGame("Uno",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2016/08/23/23/31/boa1471948306.JPG",
                    "https://view.publitas.com/31715/238002/pdfs/29e027e72495889a166168ef7381e724e457f61b.pdf",
                    "https://view.publitas.com/31715/238026/pdfs/9a62cfa203a163ccae30cc9a02ca872e2321c5d6.pdf",
                    "https://ubistatic9-a.akamaihd.net/ubicomstatic/en-US/global/game-info/Uno-game_info-Boxart-tablet-560x698_Tablet_259523.jpg",
                    2, 10,
                    "4-6",
                    30,
                    new String[] {"popular", "family", "children", "tactical"},
                    new String[] {"hand management"},
                    null),
            new BoardGame("Coup",
                    "http://www.pubmeeple.com/wp-content/uploads/Coup3.jpg",
                    "https://view.publitas.com/31715/237992/pdfs/c27b7e30e500a8b9f1b7259db53f6eb5b974e76a.pdf",
                    "https://view.publitas.com/31715/238019/pdfs/7249eafcc8b80beac36891431e3282f6803d9a0b.pdf",
                    "https://images-na.ssl-images-amazon.com/images/I/81sNAwxviTL._SL1500_.jpg",
                    2, 6,
                    "5",
                    15,
                    new String[] {"Bluffing", "Card", "Deduction"},
                    new String[] {"Memory", "Player Elimination", "Take that"},
                    null),

            new BoardGame("Shadow Hunters", "http://cf.geekdo-images.com/images/pic1215982.jpg", "",
                    "http://cf.geekdo-images.com/images/pic1215982_t.jpg",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2015/10/23/05/49/cov1445532563.jpg",
                    4, 8,
                    "7-8",
                    45,
                    new String[] {"Adventure", "Buffing", "Card", "Deduction", "Horror", "Party Game"},
                    new String[] {"Dice Rolling", "Partnership", "Player Elimination"},
                    null),
            new BoardGame("Exploding Kittens", "", "", "", "http://is1.mzstatic.com/image/thumb/Purple20/v4/88/68/ca/8868cac2-e6f3-2239-43a5-3de6e4b637a2/source/512x512bb.jpg",
                    2, 5,
                    "4-5",
                    20,
                    new String[] {"Card Game", "Humour", "Animal",},
                    new String[] {"Hand Management"},
                    null
            )
    };

//    public List<Paragraph> list = new ArrayList<>();

    public static List<BoardGame> boardGamesList = new ArrayList<>();

    public Paragraph[] getTutorialBlocks() {
        return tutorialBlocks;
    }

    public String getRulesUrl() {
        return rulesUrl;
    }

    public String[] getPlayType() {
        return playType;
    }
}
