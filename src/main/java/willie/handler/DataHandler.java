package willie.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import willie.Main;
import willie.impl.ConfigOptions;
import willie.util.DebugOutput;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataHandler {
    public static File config = new File("config","config.json");
    public static void createFile(){
        try{
            if(config.getParentFile().mkdirs()){
                DebugOutput.print("Directory created: " + config.getName());
            }
            if(config.createNewFile()){
                DebugOutput.print("File created: " + config.getName());
            }else{
                DebugOutput.print("File already exists.");
            }
        }catch(Exception e){
            DebugOutput.printError("create file error: " + config.getName());
            e.printStackTrace();
        }
    }
    public static void loadConfig(){
        ConfigOptions readConfig = (ConfigOptions) readConfig(new ConfigOptions());
        if(readConfig == null){
            writeConfig(new ConfigOptions());
        }
        Main.configOptions = (ConfigOptions) readConfig(new ConfigOptions());
    }
    public static void writeConfig(Object data){
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(config);
            gson.toJson(data, fileWriter);
            fileWriter.close();
            DebugOutput.print("write data success: " + config.getName());
        }catch(Exception e){
            DebugOutput.printError("write data error: " + config.getName());
            e.printStackTrace();
            return;
        }
    }
    public static Object readConfig(Object data){
        try{
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(config);
            data = gson.fromJson(fileReader, data.getClass());
            fileReader.close();
            DebugOutput.print("read data success: " + config.getName());
            return data;
        }catch(Exception e){
            DebugOutput.printError("read data error: " + config.getName());
            return null;
        }
    }
}
