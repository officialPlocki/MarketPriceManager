package org.japanbuild.marketmanager;

import org.japanbuild.marketmanager.util.Calculator;
import org.japanbuild.marketmanager.util.ez.DB;
import org.japanbuild.marketmanager.util.ez.Row;
import org.japanbuild.marketmanager.util.files.YamlConfiguration;
import org.japanbuild.marketmanager.util.ox.x.XList;

import java.io.File;
import java.io.IOException;

public class MarketPriceManager {

    public static void main(String[] args) throws InterruptedException {
        File file = new File("plugins/JapanCore/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()) {
            config.set("mysql.host", "host");
            config.set("mysql.user", "user");
            config.set("mysql.password", "password");
            config.set("mysql.database", "database");
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DB db = new DB(config.getString("mysql.host"), config.getString("mysql.user"), config.getString("mysql.password"), config.getString("mysql.database"));

        for(;;) {
            XList<Row> rows = db.select("SELECT * FROM market");
            rows.forEach(row -> {
                String material = row.get("material");
                long price = row.getLong("price");
                long aAmount = row.getLong("availableAmount");
                long mAmount = row.getLong("maxAmount");
                String category = row.get("category");
                db.update("UPDATE market SET price = ? WHERE material = ? AND category = ?", new Calculator().calc(aAmount, price, 1.15, mAmount), material, category);
            });
            Thread.sleep(1000*5);
        }
    }

}
