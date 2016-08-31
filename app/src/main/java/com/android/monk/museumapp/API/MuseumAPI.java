package com.android.monk.museumapp.API;

import com.android.monk.museumapp.model.DataWilayah;
import com.android.monk.museumapp.model.DataProfilMuseum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MuseumAPI {
    String ENDPOINT = "http://jendela.data.kemdikbud.go.id/api/index.php/";

//    data museum
    @GET(MuseumClientAPI.URI_GET_PROFIL_MUSEUM)
    Call<DataProfilMuseum> getProfilMuseum(@Query("museum_id") String museum_id);

    @GET(MuseumClientAPI.URI_GET_SEARCH_MUSEUM)
    Call<DataProfilMuseum> getMuseumProvinsi(@Query("kode_prop") String kode_prop);

    @GET(MuseumClientAPI.URI_GET_SEARCH_MUSEUM)
    Call<DataProfilMuseum> getMuseumKabKota(@Query("kode_kota") String kode_kota);

    @GET(MuseumClientAPI.URI_GET_SEARCH_MUSEUM)
    Call<DataProfilMuseum> getMuseumKec(@Query("kode_kec") String kode_kec);

    @GET(MuseumClientAPI.URI_GET_SEARCH_MUSEUM)
    Call<DataProfilMuseum> getMuseumNama(@Query("nama") String nama);

    @GET(MuseumClientAPI.URI_GET_SEARCH_MUSEUM)
    Call<DataProfilMuseum> getMuseumByCoordinate(@Query("lintang") String lintang, @Query("bujur") String bujur);

//    data wilayah
    @GET(MuseumClientAPI.URI_GET_WILAYAH)
    Call<DataWilayah> getDataProp();

    @GET(MuseumClientAPI.URI_GET_WILAYAH)
    Call<DataWilayah> getDataByProp(@Query("mst_kode_wilayah") String kode_prop);

    @GET(MuseumClientAPI.URI_GET_WILAYAH)
    Call<DataWilayah> getDataByKabKota(@Query("mst_kode_wilayah") String kode_kab_kota);
}
