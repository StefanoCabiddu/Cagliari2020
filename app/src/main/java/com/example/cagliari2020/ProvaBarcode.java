package com.example.cagliari2020;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ProvaBarcode extends AppCompatActivity {

    /*private static final String NEEDED_PERMISSION = ;
    private static final int REQUEST_ID = ;*/
    private BarcodeDetector detector;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private String NEEDED_PERMISSION;
    private int REQUEST_ID;

    public ProvaBarcode(BarcodeDetector detector) {
        this.detector = detector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        final TextView message = (TextView) findViewById(R.id.barcode_text);
// chiediamo di individuare QR code e EAN 13
        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE | Barcode.EAN_13)
                .build();
// verifichiamo che BarcodeDetector sia operativo
        if (!detector.isOperational()) {
            exit("Detector di codici a barre non attivabile");
            return;
        }
// istanziamo un oggetto CameraSource collegata al detector
        cameraSource = new CameraSource
                .Builder(this, detector)
                .setAutoFocusEnabled(true)
                .build();
// gestione delle fasi di vita della SurfaceView
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                activateCamera();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> items = detections.getDetectedItems();
                if (items.size() != 0)
                    runOnUiThread(new Runnable() {
                        public void run() {
                            String barcode="Rilevato: "+items.valueAt(0).displayValue;
                            message.setText(barcode);
                        }
                    });
            }
        });
    }

    private void activateCamera() {

        // verifichiamo che sia stata concessa la permission CAMERA
        if (ActivityCompat.checkSelfPermission(this, NEEDED_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, NEEDED_PERMISSION)) {
                /*
                 * OMISSIS: mostriamo finestra di dialogo che fornisce ulteriori
                 * spiegazioni sulla permission richiesta
                 */
            } else {
                ActivityCompat.requestPermissions(this, new String[]{NEEDED_PERMISSION}, REQUEST_ID);
            }
        } else {
            try {
                cameraSource.start(surfaceView.getHolder());
            } catch (IOException e) {
                exit("Errore nell'avvio della fotocamera");
            }
        }

    }

    private void exit(String s) {
    }

}
