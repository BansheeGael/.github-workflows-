package com.banshee.areadig;

/**
 * Конфигурация мода AreaDig
 */
public class Config {
    // Настройки по умолчанию
    public static int AREA_SIZE = 7;
    public static int MIN_DELAY = 50;
    public static int MAX_DELAY = 200;
    public static boolean DEBUG_MODE = true;
    public static String TOGGLE_KEY = "R";
    
    /**
     * Загрузка конфигурации
     */
    public void load() {
        System.out.println("Loading AreaDig configuration...");
        
        // В реальном моде здесь будет чтение из файла конфигурации
        // Сейчас используем значения по умолчанию
        
        System.out.println("Configuration loaded:");
        System.out.println("  Area Size: " + AREA_SIZE + "x" + AREA_SIZE);
        System.out.println("  Min Delay: " + MIN_DELAY + "ms");
        System.out.println("  Max Delay: " + MAX_DELAY + "ms");
        System.out.println("  Toggle Key: " + TOGGLE_KEY);
        System.out.println("  Debug Mode: " + DEBUG_MODE);
    }
    
    /**
     * Сохранение конфигурации
     */
    public void save() {
        System.out.println("Saving AreaDig configuration...");
        // В реальном моде здесь будет сохранение в файл
    }
    
    /**
     * Получение радиуса копки
     */
    public static int getDigRadius() {
        return AREA_SIZE / 2;
    }
    
    /**
     * Получение количества блоков в области
     */
    public static int getTotalBlocksInArea() {
        return AREA_SIZE * AREA_SIZE - 1; // -1 для центрального блока
    }
}

