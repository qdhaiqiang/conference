package com.centling.conference.util;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String ID = "";
        String qrCodeUrl = QRCodeUtil.generateQrCode(ID);
        System.out.println(qrCodeUrl);
    }

}
