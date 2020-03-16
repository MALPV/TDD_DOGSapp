package com.apps.malpv.dogs;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.malpv.dogs.adapter.PicsAdapter;
import com.apps.malpv.dogs.api.ClientApiDog;
import com.apps.malpv.dogs.api.IApiDog;
import com.apps.malpv.dogs.model.PicsListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PicsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PicsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BREED = "breed";

    // TODO: Rename and change types of parameters
    private String mBreed;
    private String mParam2;

    private IApiDog serviceApi;
    private List<String> pics;
    private RecyclerView recyclerView;
    private PicsAdapter picsAdapter;

    public PicsFragment() {
        // Required empty public constructor
    }

    public static PicsFragment newInstance(String param1) {
        PicsFragment fragment = new PicsFragment();
        Bundle args = new Bundle();
        args.putString(BREED, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBreed = getArguments().getString(BREED);
        }

        serviceApi = ClientApiDog.getRetrofitInstance().create(IApiDog.class);
        Call<PicsListResponse> picsResponseCall = serviceApi.getBreedImageList(mBreed);

        picsResponseCall.enqueue(new Callback<PicsListResponse>() {
            @Override
            public void onResponse(Call<PicsListResponse> call, Response<PicsListResponse> response) {
                pics = response.body().getPicList();

                Log.i("img", String.valueOf(pics));

                picsAdapter = new PicsAdapter(pics, getContext());
                recyclerView.setAdapter(picsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<PicsListResponse> call, Throwable t) {
                Log.e("ERROR", String.valueOf(t));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pics, container, false);
        recyclerView = view.findViewById(R.id.rvListPics);

        return view;
    }

}
