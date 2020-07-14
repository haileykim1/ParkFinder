package com.example.parkfinder

import com.google.gson.annotations.SerializedName


data class Park (
    @SerializedName("관리번호") val manageNum:String?,
    @SerializedName("공원명") val parkName : String?,
    @SerializedName("공원구분") val parkCate : String?,
    @SerializedName("소재지도로명주소") val rdnameAdr : String?,
    @SerializedName("소재지지번주소") val numAdr:String?,
    @SerializedName("위도") val lat:String?,
    @SerializedName("경도") val lng:String?,
    @SerializedName("공원면적") val areaSize:String?,
    @SerializedName("공원보유시설(운동)") val exerfas:String?,
    @SerializedName("공원보유시설(유희)") val entfas:String?,
    @SerializedName("공원보유시설(편익)") val benefas:String?,
    @SerializedName("공원보유시설(교양)") val cultfas:String?,
    @SerializedName("공원보유시설(기타)") val extrafas:String?,
    @SerializedName("지정고시일") val notifyDate:String?,
    @SerializedName("관리기관명") val managerName:String?,
    @SerializedName("전화번호") val phoneNum:String?,
    @SerializedName("데이터기준일자") val standardDate:String?,
    @SerializedName("제공기관코드") val providerCode:String?,
    @SerializedName("제공기관명") val providerName:String?
)    {


    /*
                "\n 공원보유시설(운동) : " + exerfas +
                "\n 공원보유시설(유희) : " + entfas +
                "\n 공원보유시설(편익) : " + benefas +
                "\n 공원보유시설(교양) : " + cultfas +
                "\n 공원보유시설(기타) : " + extrafas +*/

    override fun toString(): String {
        return "관리번호 : " + manageNum +
                "\n 공원구분 : " + parkCate +
                "\n 소재지도로명주소 : " + rdnameAdr +
                "\n 소재지지번주소 : " + numAdr +
                "\n 위도 : " + lat +
                "\n 경도 : " + lng +
                "\n 공원면적 : " + areaSize +
                "\n 지정고시일 : " + notifyDate +
                "\n 관리기관명 : " + managerName +
                "\n 전화번호 : " + phoneNum +
                "\n 데이터기준일자 : " + standardDate +
                "\n 제공기관코드 : " + providerCode +
                "\n 제공기관명 : " + providerName
    }


}