package cache;

import dao.HCardDao;

public class CardCacheService {
    private static final Thread timer;
    private static final HCardDao hdao = new HCardDao();

    static {
        timer = new Thread(() -> {
            while (true) {
                CardCache.cardsInside = hdao.get();
                System.out.println("***********Cards updated***********");
                try {
                    Thread.sleep(600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timer.start();
    }

}
