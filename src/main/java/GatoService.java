import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatoService {

    public static void verGatos() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();
        json = json.substring(1, json.length() - 1);

        Gson gson = new Gson();
        Gatos gatito = gson.fromJson(json, Gatos.class);

        Image image = null;
        ImageIcon imageicon = null;

        try{
            URL url = new URL(gatito.getUrl());
            image = ImageIO.read(url);

            image = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);

            imageicon = new ImageIcon(image);

        }catch (Exception e){

            e.printStackTrace();
        }

        String menu = "Opciones: \n"
                +     " 1. Ver otra imagen \n"
                +     " 2. Favorito \n"
                +     " 3. Ver Favoritos \n"
                +     " 4. Volver";

        String[] botones = {"Ver otra imagen", "Favorito", "Ver Favoritos", "Volver"};
        String id_gato = gatito.getId();
        String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, imageicon, botones
        , botones[0]);

        int seleccion = -1;

        for (int i = 0; i < botones.length; i++) {
            if(opcion.equals(botones[i])){
                seleccion = i;
            }
        }
        
        switch (seleccion){
            case 0:
                GatoService.verGatos();
                break;
            case 1:
                GatoService.guardarFavorito(gatito);
                break;
            case 2:
                GatoService.verFavoritos();
                break;
            default:
                break;
        }
    }
    public static void guardarFavorito(Gatos gato){

        try{

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n  \"image_id\": \"" + gato.getId() + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", Gatos.apikey)
                    .build();
            Response response = client.newCall(request).execute();

        }catch (Exception e){

            e.printStackTrace();
        }

    }
    public static void verFavoritos(){
        try{

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("GET", null)
                    .addHeader("x-api-key", "4c5a157d-2e7a-49b4-af54-0637e467d3a9")
                    .build();
            Response response = client.newCall(request).execute();

            String json = response.body().string();
            Gson gson = new Gson();

            GatosFav[] gatos = gson.fromJson(json, GatosFav[].class);

            MarcoGatos marco = new MarcoGatos(gatos);

        }catch(Exception e){


        }

    }
}
