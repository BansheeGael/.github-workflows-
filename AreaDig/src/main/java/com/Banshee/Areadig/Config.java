package com.banshee.areadig;  // ⬅️ ИЗМЕНЕНО: yourname → banshee

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class Config {
    public static int AREA_SIZE = 7;
    public static int MIN_DELAY = 50;
    public static int MAX_DELAY = 200;
    
    public static void load(File file) {
        Configuration config = new Configuration(file);
        config.load();
        
        AREA_SIZE = config.getInt("areaSize", "general", 7, 3, 11, "Размер области копки");
        MIN_DELAY = config.getInt("minDelay", "timing", 50, 20, 500, "Минимальная задержка между блоками");
        MAX_DELAY = config.getInt("maxDelay", "timing", 200, 50, 1000, "Максимальная задержка между блоками");
        
        config.save();
    }
}