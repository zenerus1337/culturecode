package com.culturecode.main.app.ui.home;

import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.LoginResponse;
import com.culturecode.main.app.connection.PlaceRequest;
import com.culturecode.main.app.localisations.LocalisationRequest;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import kotlin.Triple;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class localisations {
    public interface DataReadyCallback {
        void onDataReady();
    }
    private DataReadyCallback callback;

    public void setCallback(DataReadyCallback callback) {
        this.callback = callback;
    }
    public Vector<Triple<String, Double, Double>> listedPlaces = new Vector<>();

    public List<String> qrCode = new ArrayList<>();
    public HashMap<String, String> placeSentences = new HashMap<>();
    public HashMap<String, List<String>> placeFacts = new HashMap<>();
    public LatLng wroclaw = new LatLng(51.10959846591886, 17.034598572454573);

    public void initializeLocalizations() {
        Call<List<PlaceRequest>> localRequestCall = ApiClient.getService().getAllPlaces();
        localRequestCall.enqueue(new Callback<List<PlaceRequest>>() {
            @Override
            public void onResponse(Call<List<PlaceRequest>> call, Response<List<PlaceRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PlaceRequest> placeRequests = response.body();
                    for (PlaceRequest place : placeRequests) {
                        listedPlaces.add(new Triple<>(place.getName(), Double.parseDouble(place.getLat()), Double.parseDouble(place.getLang())));
                        qrCode.add(place.getQrCode());
                    }
                }
                if (callback != null) {
                    callback.onDataReady();
                }
            }

            @Override
            public void onFailure(Call<List<PlaceRequest>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });






          //listedPlaces.add(new Triple<>("Politechnika Wrocławska", 51.10760741877494, 17.059446815753738));
        //listedPlaces.add(new Triple<>("Panorama Racławicka", 51.11029266157793, 17.04498390927858));
//        listedPlaces.add(new Triple<>("Rynek", 51.11041273629406, 17.03107984507234));
//        listedPlaces.add(new Triple<>("Sky Tower", 51.09454744701896, 17.018963211893173));
//        listedPlaces.add(new Triple<>("Hala Targowa", 51.112528800156156, 17.03973826135461));
//        listedPlaces.add(new Triple<>("Hala Stulecia", 51.10679766971045, 17.07728034508164));
//
//        placeSentences.put("Politechnika Wrocławska", "Jedna z najlepszych uczelni technicznych w Polsce");
//        placeSentences.put("Panorama Racławicka", "Monumentalne dzieło malarstwa batalistycznego");
//        placeSentences.put("Rynek", "Jest jednym z największych rynków średniowiecznych w Europie");
//        placeSentences.put("Sky Tower", "Najwyższy budynek mieszkalny w Polsce");
//        placeSentences.put("Hala Targowa", "Zabytkowa hala targowa we Wrocławiu");
//        placeSentences.put("Hala Stulecia", "Została wpisana na listę światowego dziedzictwa UNESCO");
//
//        List<String> politechnikaFacts = new ArrayList<>();
//        politechnikaFacts.add("Politechnika Wrocławska powstała w 1945 roku.");
//        politechnikaFacts.add("Studenci Politechniki Wrocławskiej mają dostęp do najnowocześniejszych laboratoriów.");
//        politechnikaFacts.add("Na uczelni działa ponad 100 kół naukowych.");
//
//        List<String> panoramaFacts = new ArrayList<>();
//        panoramaFacts.add("Panorama Racławicka została namalowana w latach 1893-1894 przez Jana Stykę i Wojciecha Kossaka.");
//        panoramaFacts.add("To jedno z największych dzieł malarskich na świecie, o wymiarach 15x114 metrów.");
//        panoramaFacts.add("Panorama Racławicka przedstawia bitwę pod Racławicami.");
//
//        List<String> rynekFacts = new ArrayList<>();
//        rynekFacts.add("Rynek we Wrocławiu ma powierzchnię 3,8 hektara.");
//        rynekFacts.add("Jest jednym z największych rynków w Polsce i Europie.");
//        rynekFacts.add("Architektura rynku to głównie zabytkowe kamienice.");
//
//        List<String> skyTowerFacts = new ArrayList<>();
//        skyTowerFacts.add("Sky Tower ma 212 metrów wysokości i 51 pięter.");
//        skyTowerFacts.add("Jest najwyższym budynkiem mieszkalnym we Wrocławiu i w Polsce.");
//        skyTowerFacts.add("Z najwyższych pięter budynku roztacza się widok na całe miasto.");
//
//        List<String> halaTargowaFacts = new ArrayList<>();
//        halaTargowaFacts.add("Hala Targowa została otwarta w 1908 roku.");
//        halaTargowaFacts.add("To jedno z najważniejszych miejsc handlowych we Wrocławiu.");
//        halaTargowaFacts.add("W hali znajduje się ponad 100 stoisk z różnorodnymi produktami.");
//
//        List<String> halaStuleciaFacts = new ArrayList<>();
//        halaStuleciaFacts.add("Hala Stulecia została wybudowana w 1913 roku z okazji 100-lecia bitwy pod Lipskiem.");
//        halaStuleciaFacts.add("Została zaprojektowana przez Maxa Berga.");
//        halaStuleciaFacts.add("W hali odbywały się liczne wydarzenia, takie jak występy artystyczne czy kongresy.");
//
//        placeFacts.put("Politechnika Wrocławska", politechnikaFacts);
//        placeFacts.put("Panorama Racławicka", panoramaFacts);
//        placeFacts.put("Rynek", rynekFacts);
//        placeFacts.put("Sky Tower", skyTowerFacts);
//        placeFacts.put("Hala Targowa", halaTargowaFacts);
//        placeFacts.put("Hala Stulecia", halaStuleciaFacts);
   }
}
