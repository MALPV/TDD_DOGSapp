package com.apps.malpv.dogs.ui;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.malpv.dogs.IComunica;
import com.apps.malpv.dogs.R;
import com.apps.malpv.dogs.adapter.BreedAdapter;
import com.apps.malpv.dogs.api.ClientApiDog;
import com.apps.malpv.dogs.api.IApiDog;
import com.apps.malpv.dogs.model.BreedsListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class BreedsFragment extends Fragment {

    private IApiDog serviceApi;
    private List<String> breeds;

    private RecyclerView recyclerView;
    private BreedAdapter breedAdapter;

    //Interfaz para interconectar este fragmento con el activity padre donde lo incluyamos
    private OnListFragmentInteractionListener mListener;

    Activity activity;
    IComunica iComunica;

    public BreedsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceApi = ClientApiDog.getRetrofitInstance().create(IApiDog.class);
        Call<BreedsListResponse> breedsResponseCall = serviceApi.getBreedList();

        breedsResponseCall.enqueue(new Callback<BreedsListResponse>() {
            @Override
            public void onResponse(Call<BreedsListResponse> call, Response<BreedsListResponse> response) {
                breeds = response.body().getBreedList();
                Log.i("DATOS", String.valueOf(breeds));

                breedAdapter = new BreedAdapter(breeds, getContext());
                recyclerView.setAdapter(breedAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                breedAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), breeds.get(recyclerView.getChildAdapterPosition(view))
                                , Toast.LENGTH_SHORT).show();

                        iComunica.sendBreedSelected(breeds.get(recyclerView.getChildAdapterPosition(view)));
                    }
                });
            }

            @Override
            public void onFailure(Call<BreedsListResponse> call, Throwable t) {
                Log.e("ERROR", String.valueOf(t));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breeds, container, false);

        recyclerView = view.findViewById(R.id.rvListBreeds);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity= (Activity) context;
            iComunica = (IComunica) this.activity;
        }

        /*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Uri uri);
    }

}
