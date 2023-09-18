package org.japanbuild.marketmanager.util.files;

import java.io.File;
import java.io.IOException;

public class FileBuilder {

    private final File file;
    private final YamlConfiguration yml;

    public FileBuilder(String path) {
        file = new File(path);
        if(!file.exists()) {
            if(path.contains(File.separator)) {
                file.getParentFile().mkdir();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yml = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getYaml() {
        return yml;
    }

    public File getFile() {
        return file;
    }

    public void save() {
        try {
            yml.save(file);
        } catch (IOException ignored) {
        }
    }

}
