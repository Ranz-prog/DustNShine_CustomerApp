package com.example.dustnshine.models;

public class favorite_model {

    private int companyImgFav;
    private  String companyNameFav, companyAddressFav,companyRatingFav;

    public favorite_model(int companyImgFav, String companyNameFav, String companyAddressFav, String companyRatingFav) {
        this.companyImgFav = companyImgFav;
        this.companyNameFav = companyNameFav;
        this.companyAddressFav = companyAddressFav;
        this.companyRatingFav = companyRatingFav;
    }

    public int getCompanyImgFav() {
        return companyImgFav;
    }

    public void setCompanyImgFav(int companyImgFav) {
        this.companyImgFav = companyImgFav;
    }

    public String getCompanyNameFav() {
        return companyNameFav;
    }

    public void setCompanyNameFav(String companyNameFav) {
        this.companyNameFav = companyNameFav;
    }

    public String getCompanyAddressFav() {
        return companyAddressFav;
    }

    public void setCompanyAddressFav(String companyAddressFav) {
        this.companyAddressFav = companyAddressFav;
    }

    public String getCompanyRatingFav() {
        return companyRatingFav;
    }

    public void setCompanyRatingFav(String companyRatingFav) {
        this.companyRatingFav = companyRatingFav;
    }
}
