package io.github.project_travel_mate.utilities;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.project_travel_mate.R;

import static utils.Constants.BAR_CODE_HEIGHT;
import static utils.Constants.BAR_CODE_WIDTH;
import static utils.Constants.USER_NO;

public class BarcodeFragment extends Fragment {

    private SharedPreferences mSharedPreferences;

    @BindView(R.id.qrcode_img)
    ImageView qrCodeView;


    public BarcodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment QRCodeFragment.
     */
    public static BarcodeFragment newInstance() {
        BarcodeFragment fragment = new BarcodeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        ButterKnife.bind(this, view);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        createBarCode();

        return view;
    }

    public void createBarCode() {

        //getting details to be encoded in qr code
        String userNo = mSharedPreferences.getString(USER_NO, null);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(userNo,
                    BarcodeFormat.CODE_128, BAR_CODE_WIDTH, BAR_CODE_HEIGHT);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            //Creating bitmap for generated 2D matrix
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            //saving the generated qr code in device
            String path = Environment.getExternalStorageDirectory().getPath();
            File qrCodeFile = new File(path + "/travelbao/barcodes");
            qrCodeFile.mkdir();
            //for providing name to image
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);

            String fname = "Image-" + n + ".jpg";
            File file = new File(qrCodeFile, fname);

            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //displaying QRCode on screen
            qrCodeView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }



}
