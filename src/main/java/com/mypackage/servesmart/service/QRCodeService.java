package com.mypackage.servesmart.service;

import com.google.zxing.WriterException;
import com.mypackage.servesmart.utils.QRCodeGenerator;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRCodeService {
    public byte[] generateQRCodeImage(String qrData) throws WriterException, IOException {
        return QRCodeGenerator.generateQRCode(qrData, 200, 200);
    }
}

