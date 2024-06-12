package com.culturecode.main.app.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.culturecode.main.app.R;
import com.culturecode.main.app.SentenceActivity;
import com.culturecode.main.app.databinding.FragmentGalleryBinding;
import com.culturecode.main.app.ui.home.localisations;
import com.culturecode.main.app.CommentsActivity;

import java.util.ArrayList;

import kotlin.Pair;
import kotlin.Triple;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ListView placeEl;
    public localisations local = new localisations();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        placeEl = root.findViewById(R.id.places_list);
        ArrayList<String> placeNames = new ArrayList<>();

        local.initializeLocalizations();
        local.setCallback(new localisations.DataReadyCallback() {
            @Override
            public void onDataReady() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Triple<String, Double, Double> place : local.listedPlaces) {
                            placeNames.add(place.getFirst());
                        }
                        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, placeNames);
                        placeEl.setAdapter(placeAdapter);
                    }
                });
            }
        });
        placeEl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String place = placeNames.get(position);
                String correctSentence = local.placeSentences.get(place);

                Intent intent = new Intent(getActivity(), SentenceActivity.class);
                intent.putExtra("place", place);
                intent.putExtra("correctSentence", correctSentence);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}