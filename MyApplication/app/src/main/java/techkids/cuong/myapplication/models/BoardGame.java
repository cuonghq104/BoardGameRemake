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

//    private String rules;

    private Paragraph[] ruleParagraphs;

    private String description;
    private Publisher publisher;

    public BoardGame(String name, String imageUrl, String detailUrl, String rulesUrl, String thumbUrl, int minPlayer, int maxPlayer, String favoritePlayer, int playingTime, String[] categories, String[] playType,  Paragraph[] ruleParagraphs, String description, Publisher publisher) {
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
        this.playType = playType;
        this.description = description;
//        this.rules = rules;
        this.ruleParagraphs = ruleParagraphs;
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

//    public String getRules() {
//        return rules;
//    }


    public Paragraph[] getRuleParagraphs() {
        return ruleParagraphs;
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
                    new String[]{"buffing", "deduction", "horror", "murder", "mystery", "party"},
                    new String[]{"partnership", "player elimination", "role playing", "voting"},
                    new Paragraph[] {
                            new Paragraph(Paragraph.COMBINE_TYPE_IMAGE_ABOVE, "Các nhân vật trong trò chơi", "Trò chơi gồm rất nhiều nhân vật, mỗi nhân vật một chức năng khác nhau, hoạt động trong phạm vi khác nhau và với các mục đích cũng khác nhau nốt", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/uw1.jpg", null),
                            new Paragraph(Paragraph.COMBINE_TYPE_IMAGE_LEFT, "Phù thủy", "Thầy phù thủy quyền năng, đêm đêm thức dậy đóng vai trò bác sĩ và tử thần, nôm na là sẽ được quyền giết và cứu một người 1 đêm duy nhất", "https://github.com/cuonghq104/Images/blob/master/Card/dx-witch_1024x1024.png?raw=true", new String[] {"Phù thủy", "Witch", "Quyền năng", "Chức sắc", "Chức năng"}),
                            new Paragraph(Paragraph.COMBINE_TYPE_IMAGE_RIGHT, "Ma Sói", "Những con ma sói quỷ quyệt, đêm đêm thức dậy nhìn mặt nhau, bắt tay giết chết một người, người là mục tiêu cắn có thể được cứu sống bởi các chức năng khác", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/dx-werewolf_1024x1024.png", new String[] {"Ma Sói", "Werewolf", "Sói", "Werewolves"}),
                            new Paragraph(Paragraph.COMBINE_TYPE_IMAGE_LEFT, "Tiên tri", "Ông lớn của làng, thánh gánh team, thức dậy soi và bóc phốt những con sói xảo quyệt, đại khái là : Thức dậy chỉ vào một người dân, nếu chỉ đúng sói, già làng gật đầu", "https://raw.githubusercontent.com/cuonghq104/Images/master/Card/dx-seer_1024x1024.png", new String[] {"Tiên tri", "Seer", "Chức sắc", "Chức năng"})
                    },
                    "Werewolf takes place in a small village which is haunted by werewolves.\n" +
                            "\n" +
                            "Each player is secretly assigned a role - Werewolf, Villager, or Seer (a special Villager). There is also a Moderator who controls the flow of the game.\n" +
                            "\n" +
                            "The game alternates between night and day phases. At night, the Werewolves secretly choose a Villager to kill. Also, the Seer (if still alive) asks whether another player is a Werewolf or not. During the day, the Villager who was killed is revealed and is out of the game. The remaining Villagers then vote on the player they suspect is a Werewolf. That player reveals his/her role and is out of the game.\n" +
                            "\n" +
                            "Werewolves win when there are an equal number of Villagers and Werewolves. Villagers win when they have killed all Werewolves. Werewolf is a social game that requires no equipment to play, and can accommodate almost any large group of players.\n" +
                            "\n",
                    new Publisher("Asterion Press", "http://www.negoziogiochi.it/media/catalog/category/Asterion_400B1_1.jpg")),
            new BoardGame("Uno",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2016/08/23/23/31/boa1471948306.JPG",
                    "https://view.publitas.com/31715/238002/pdfs/29e027e72495889a166168ef7381e724e457f61b.pdf",
                    "https://view.publitas.com/31715/238026/pdfs/9a62cfa203a163ccae30cc9a02ca872e2321c5d6.pdf",
                    "https://ubistatic9-a.akamaihd.net/ubicomstatic/en-US/global/game-info/Uno-game_info-Boxart-tablet-560x698_Tablet_259523.jpg",
                    2, 10,
                    "4-6",
                    30,
                    new String[]{"popular", "family", "children", "tactical"},
                    new String[]{"hand management"},
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
                    new String[]{"Bluffing", "Card", "Deduction"},
                    new String[]{"Memory", "Player Elimination", "Take that"},
                    null,
                    "",
                    null),

            new BoardGame("Shadow Hunters", "http://cf.geekdo-images.com/images/pic1215982.jpg", "",
                    "http://cf.geekdo-images.com/images/pic1215982_t.jpg",
                    "http://boardgame.vn/uploads/u/boardgame.vn/product/2015/10/23/05/49/cov1445532563.jpg",
                    4, 8,
                    "7-8",
                    45,
                    new String[]{"Adventure", "Buffing", "Card", "Deduction", "Horror", "Party Game"},
                    new String[]{"Dice Rolling", "Partnership", "Player Elimination"},
                    null,
                    "",
                    null),
            new BoardGame("Exploding Kittens", "", "", "", "http://is1.mzstatic.com/image/thumb/Purple20/v4/88/68/ca/8868cac2-e6f3-2239-43a5-3de6e4b637a2/source/512x512bb.jpg",
                    2, 5,
                    "4-5",
                    20,
                    new String[]{"Card Game", "Humour", "Animal",},
                    new String[]{"Hand Management"},
                    null,
                    "",
                    null
            )
    };

//    public List<Paragraph> list = new ArrayList<>();

    public static List<BoardGame> boardGamesList = new ArrayList<>();


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
