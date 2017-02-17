package techkids.cuong.myapplication.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cuong on 1/5/2017.
 */
public class BoardGame implements Serializable {

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

    private String description;

    private Publisher publisher;

    public BoardGame(String name, String imageUrl, String detailUrl, String rulesUrl, String thumbUrl, int minPlayer, int maxPlayer, String favoritePlayer, int playingTime, String[] categories, String[] playType, Paragraph[] tutorialBlocks, String description, Publisher publisher) {
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
        this.description = description;
        this.publisher = publisher;
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
            new BoardGame("Werewolf basic - a very basic game",
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
                            Paragraph.createText("Trình tự game", "1. Giai đoạn ban đêm: Mọi người nhắm mắt, Quản Trò gọi vai trò đặc biệt nào thì vai trò ấy mở mắt và thực hiện chức năng của mình trong-yên-lặng. Trình tự gọi của Quản Trò như sau: \n" +
                                            "\t\t1. Ăn trộm (Chỉ đêm đầu tiên)\n" +
                                            "\t\t2. Cupid (Chỉ đêm đầu tiên)\n" +
                                            "\t\t3. 2 người yêu nhau (Chỉ đêm đầu tiên)\n" +
                                            "\t\t4.Bảo vệ\n" +
                                            "\t\t5.Sói\n" +
                                            "\t\t6.Tiên Tri\n" +
                                            "\t\t7.Phù Thủy\n" +
                                            "\t\t8.Thổi sáo\n" +
                                            "\t\t9.Những người bị thôi miên\n" +
                                            "\t\t10.Già Làng (Chỉ đêm đầu tiên)\n" +
                                            "\t\t11.Thợ Săn (Chỉ đêm đầu tiên)\n" +
                                            "2. Ban ngày: Quản trò ra hiệu mọi người mở mắt, thông báo những ai đã chết đêm qua. Sau đó, dân làng bình bầu treo cổ một người bị nghi ngờ là Ma Sói trong ban ngày (Có thể hoãn không treo). Nếu có 2 người cùng có số phiếu bầu treo như nhau thì không ai bị treo cả.",
                                    new String[] {"ban ngày", "ban đêm"}),
                            Paragraph.createCombine("Tiên tri", "Mỗi đêm, khi được gọi dậy Tiên Tri sẽ chỉ một người. Nếu người đó là Sói Quản trò sẽ gật đầu.\n" , "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/seer.png",
                                    new String[] {"sói", "mỗi đêm"}),
                            Paragraph.createCombine("Thợ săn", "Khi thợ săn chết, dù là dưới bất kỳ hình thức nào cũng có thể chọn một người chơi khác và kéo hắn xuống “Tuyền đường” cùng Thợ Săn", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/hunter.png",
                                    new String[] {"chết", "mỗi đêm", "giết"}),
                            Paragraph.createCombine("Phù thủy", "Phù Thủy có hai bình thuốc: Một bình dùng để cứu một người, còn một bình dùng để giết một người. Mỗi đêm, Quản trò khi gọi Phù Thủy dậy sẽ cho Phù Thủy biết người bị giết bởi Sói đêm đó, và Phù Thủy được quyền quyết định xem có cứu người ấy hay không. Sau đó, quản trò sẽ hỏi xem Phù Thủy có dùng bình giết giết ai hay không. Một khi đã dùng bình thì Phù Thủy sẽ mất đi chức năng tương ứng, tuy nhiên vẫn được gọi dậy mỗi đêm và biết ai chết. Lưu ý: Nếu đêm đó Bảo Vệ đã Bảo Vệ đúng người thì Quản trò sẽ lắc tay, ra dấu là không ai chết cả.", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/witch.png",
                                    new String[] {"giết", "cứu"}),
                            Paragraph.createCombine("Cupid", "Đầu mỗi ván chơi, Cupid sẽ được gọi dậy và chọn ra hai người yêu nhau. Cupid sau đó nhắm mắt lại và hai người yêu nhau sẽ được Quản Trò gọi dậy để biết mặt và Vai Trò của nhau. Nếu hai người thuộc hai phe khác nhau (Sói vs Dân) thì họ thành phe thứ ba với nhiệm vụ là hai người cuối cùng sống sót.", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/cupid.png",
                                    new String[] {"đêm đầu tiên", "sói", "dân"}),
                            Paragraph.createCombine("Ma Sói", "Mỗi đêm thức dậy, các Sói sẽ biết mặt lẫn nhau và sẽ cùng thống nhất giết một người. Sói có quyền không giết, và cũng có quyền tự giết lẫn nhau.", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/WereWolf.jpg",
                                    new String[] {"mỗi đêm", "giết", "tiên tri"}),
                            Paragraph.createCombine("Dân làng", "Năng lực rất đơn giản: Chết khi bị giết. Ngoài ra còn có năng lực bỏ phiếu treo người khác", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/villager.png",
                                    new String[] {"chết, giết, bỏ phiếu"}),
                            Paragraph.createText("Luật chơi", "Cách chơi của trò chơi là mỗi người sẽ được nhận lần lượt 1 lá bài để biết chức năng của mình, sau đó sẽ được quản trò bảo đi ngủ trong từng đêm. Mỗi đêm, quản trò gọi từng chức năng đặc biệt thức dậy như sói, cupid, witch ... để thực hiện công việc của mình rồi tiếp tục đi ngủ. Sau khi gọi lên hết cách chức năng. Quản trò thông báo mọi người mở mắt dậy và sẽ cho biết đêm qua ai bị giết.\n" +
                                            "\n" +
                                            "Sáng đó, mọi người sẽ cùng ngồi thảo luận với nhau, và bằng cơ sở lý luận, lập luận hay troll nhau gì gì đó ... mà cùng nhau tìm ra và treo cổ những con sói tinh ma.\n" +
                                            "\n" +
                                            "Còn về phía phe sói, thì cùng bảo vệ nhau để ly gián dân làng giành được chiến thắng cuối cùng.\n" +
                                            "\n" +
                                            "Nếu một người bị giết hay loại khỏi trò chơi vì quy phạm nội quy game, quản trò sẽ không cho biết chức năng của họ cho đến khi kết thúc game.\n" +
                                            "\n" +
                                            "Thứ tự gọi mỗi nhân vật trong đêm, phải theo 1 thứ tự nhất định không được thay đổi. Nếu quản trò gọi thứ tự sai, đôi khi sẽ gây sự nhầm lẫn hoặc sai sót trong game.\n" +
                                            "\n" +
                                            "Người chơi đã bị loại khỏi game do bị giết hoặc vi phạm, phải tuyệt đối tuân thủ và tôn trọng những người chơi khác bằng cách giữ im lặng và theo dõi game tiếp tục diễn ra.\n" +
                                            "\n" +
                                            "Mỗi buổi sáng, dân làng có tối đa 2 phút để cùng thảo luận với nhau.\n" +
                                            "\n" +
                                            "Sau đó có 5 giây để chỉ tay về phía người mình tình nghi, nếu ai bị chỉ tay nhiều nhất sẽ có 1 phút để tự bào chữa cho mình.\n" +
                                            "\n" +
                                            "Trong quá trình 1 phút tự bào chữa của người bị tình nghi, những người còn lại không được phép lên tiếng, nếu lên tiếng sẽ bị loại khỏi game. Trường hợp lên tiếng khi được người tình nghi hỏi trong lúc đang bào chữa cho mình, người lên tiếng sẽ bị mất quyền bỏ phiếu. Nếu nói chuyện ngoài luồng và cãi nhau, người chơi bị loại khỏi game.\n" +
                                            "Hết 1 phút, những người có quyền bỏ phiếu sẽ biểu quyết xem có chấp nhận phần tự bào chữa của người bị tình nghi không. Nếu số phiếu chấp nhận nhiều hơn số phiếu không chấp nhận thì người đó được sống và tiếp tục trò chơi, ngược lại thì người đó sẽ chết (quản trò sẽ thu lại lá bài chức năng)\n" +
                                            "\n" +
                                            "Dù người chơi bị chết giữ chức năng quan trọng nào đó, quản trò vẫn không được tiết lộ nên mỗi đêm vẫn phải gọi hết tất cả các chức năng của trò chơi lên để đánh lạc hướng người chơi và làm cơ sở cho phe sói có thời gian giả dạng đánh lừa dân làng. Do đó, việc bạn có chức năng đặc biệt nào đó mà bạn bị giết rồi mà quản trò vẫn gọi chức năng đó là điều hoàn toàn bình thường, đừng tỏ ra thắc mắc mà hãy im lặng theo dõi đến khi kết thúc trò chơi.",
                                    new String[] {})
                    },
                    "Werewolf takes place in a small village which is haunted by werewolves.\n" +
                            "\n" +
                            "Each player is secretly assigned a role - Werewolf, Villager, or Seer (a special Villager). There is also a Moderator who controls the flow of the game.\n" +
                            "\n" +
                            "The game alternates between night and day phases. At night, the Werewolves secretly choose a Villager to kill. Also, the Seer (if still alive) asks whether another player is a Werewolf or not. During the day, the Villager who was killed is revealed and is out of the game. The remaining Villagers then vote on the player they suspect is a Werewolf. That player reveals his/her role and is out of the game.\n" +
                            "\n" +
                            "Werewolves win when there are an equal number of Villagers and Werewolves. Villagers win when they have killed all Werewolves. Werewolf is a social game that requires no equipment to play, and can accommodate almost any large group of players.",
                    new Publisher("Asterion Press", "http://www.negoziogiochi.it/media/catalog/category/Asterion_400B1_1.jpg")),
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
                    null,
                    "",
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
                    null,
                    "",
                    null),

            new BoardGame("Shadow Hunters", "http://cf.geekdo-images.com/images/pic1215982.jpg", "",
                    "http://cf.geekdo-images.com/images/pic1215982_t.jpg",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2015/10/23/05/49/cov1445532563.jpg",
                    4, 8,
                    "7-8",
                    45,
                    new String[] {"Adventure", "Buffing", "Card", "Deduction", "Horror", "Party Game"},
                    new String[] {"Dice Rolling", "Partnership", "Player Elimination"},
                    null,
                    "",
                    null),
            new BoardGame("Exploding Kittens", "", "", "", "http://is1.mzstatic.com/image/thumb/Purple20/v4/88/68/ca/8868cac2-e6f3-2239-43a5-3de6e4b637a2/source/512x512bb.jpg",
                    2, 5,
                    "4-5",
                    20,
                    new String[] {"Card Game", "Humour", "Animal",},
                    new String[] {"Hand Management"},
                    null,
                    "",
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

    public String getDescription() {
        return description;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
