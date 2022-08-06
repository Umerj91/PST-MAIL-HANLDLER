package com.example.pstmailhandler;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class FolderDetail {
  public  int fid;
  public  int parentid;
  public String name;
  ArrayList<FolderDetail> childrens;
  public  FolderDetail(){

      childrens=new ArrayList<>();
  }
  public FolderDetail(int f,int p,String n)
  {
      fid=f;
      parentid=p;
      name=n;
      childrens=new ArrayList<>();
  }
  @RequiresApi(api = Build.VERSION_CODES.N)
  public ArrayList<FolderDetail> foldersHerirachy(ArrayList<FolderDetail> allfolders)
  {
    ArrayList<FolderDetail> folder_herirachy= (ArrayList<FolderDetail>) allfolders.stream().filter( x->x.parentid==-1 );

    ArrayList<FolderDetail> subfolders= (ArrayList<FolderDetail>) allfolders.stream().filter( x->x.parentid!=-1 );



    for(int i=0;i<folder_herirachy.size();i++)
    {
           linkSubFolders( subfolders,folder_herirachy.get( i ) );
    }
    return  folder_herirachy;

  }
  @RequiresApi(api = Build.VERSION_CODES.N)
  private   void linkSubFolders(ArrayList<FolderDetail> subFolders, FolderDetail f)
  {
      ArrayList<FolderDetail> childs= (ArrayList<FolderDetail>) subFolders.stream().filter( s->s.parentid==f.fid );
     for(int i=0;i<childs.size();i++)
     {
         f.childrens.add( childs.get( i ) );
     }

     if(childs.size()==0)
         return;;

         for(int i=0;i<f.childrens.size();i++)
         {
            linkSubFolders( subFolders,f.childrens.get( i ) );
         }

  }
}


