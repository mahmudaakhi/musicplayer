package com.r.myapplication;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class Video extends Fragment {

    RecyclerView myRecyclerView;
    RecyclerViewAdapter obj_adapter;
    public static int REQUEST_PERMISSION = 1;
    File directory;
    boolean bolean_permission;
    public static ArrayList<File> fileArrayList = new ArrayList<>();

    public Video() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        myRecyclerView = view.findViewById(R.id.recyclerView);

        //Phone Memory And SD Card
        directory = new File("/mnt/");

        //SD Card
        //directory = new File("/storage/");

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        myRecyclerView.setLayoutManager(manager);

        permissionForVideo();
        return view;
    }

    private void permissionForVideo() {

        if ((ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){


            if((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE))){
            }
            else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }

        }
        else{
            bolean_permission = true;
            getFile(directory);
            obj_adapter = new RecyclerViewAdapter(getActivity(),fileArrayList);
            myRecyclerView.setAdapter(obj_adapter);

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSION){

            if(grantResults.length>0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){

                bolean_permission = true;
                getFile(directory);
                obj_adapter = new RecyclerViewAdapter(getContext(),fileArrayList);
                myRecyclerView.setAdapter(obj_adapter);

            }
            else{
                Toast.makeText(getContext(), "Please Allow the Permission", Toast.LENGTH_SHORT).show();
            }



        }
    }

    public ArrayList<File> getFile(File directory){

        File listFile[] = directory.listFiles();
        if(listFile!=null && listFile.length>0){

            for(int i = 0;i<listFile.length;i++){

                if(listFile[i].isDirectory()){

                    getFile(listFile[i]);

                }

                else{

                    bolean_permission = false;
                    if(listFile[i].getName().endsWith(".mp4")){

                        for(int j=0;j<fileArrayList.size();j++){

                            if(fileArrayList.get(j).getName().equals(listFile[i].getName())){

                                bolean_permission = true;


                            }else{

                            }

                        }

                        if(bolean_permission){
                            bolean_permission =false;
                        }
                        else{
                            fileArrayList.add(listFile[i]);
                        }

                    }


                }


            }


        }
        return fileArrayList;
    }
}