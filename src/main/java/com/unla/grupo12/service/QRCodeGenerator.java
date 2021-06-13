package com.unla.grupo12.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

  public static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException{
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    @SuppressWarnings("rawtypes")
    HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    hints.put(EncodeHintType.MARGIN	,2);

    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    //BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

    Path path = FileSystems.getDefault().getPath(filePath);
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
  }

  public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException{
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

    MatrixToImageWriter.writeToStream(bitMatix, "PNG", pngOutputStream);

    byte[] pngDate = pngOutputStream.toByteArray();

    return pngDate;
  }


}
