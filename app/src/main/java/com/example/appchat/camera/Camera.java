//package com.example.appchat.camera;
//
//import android.graphics.SurfaceTexture;
////import android.media.ExifInterface;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.LayoutInflater;
//import android.view.TextureView;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import com.example.appchat.R;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class Camera extends Fragment implements TextureView.SurfaceTextureListener, View.OnClickListener {
//
//    private TextureView tv_;
//    private android.hardware.Camera camera;
//    private int cId = android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.actitivity_camrera,container,false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        tv_ = view.findViewById(R.id.tv_);
//        tv_.setSurfaceTextureListener(this);
//        view.findViewById(R.id.btn_capture).setOnClickListener(this);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if ( camera != null){
//            camera.stopPreview();
//            camera.release();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(camera != null ){
//            try {
//                camera.reconnect();
//                camera.startPreview();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void configCamera(){
//        System.out.println("fsdfdf");
//        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
//        android.hardware.Camera.getCameraInfo(cId, info);
//        camera.setDisplayOrientation(info.orientation);
//        android.hardware.Camera.Size sizePi = camera.getParameters().getSupportedPictureSizes().get(0);
//        camera.getParameters().setPictureSize(sizePi.width, sizePi.height);
//
//        android.hardware.Camera.Size sizePreView =
//                camera.getParameters().getSupportedPreviewSizes().get(0);
//        camera.getParameters().setPreviewSize(sizePreView.width, sizePreView.height);
//        float heightView = (float)sizePreView.width * tv_.getWidth()/sizePreView.height;
//        tv_.getLayoutParams().height=(int)heightView;
//        tv_.requestLayout();
//
//
////        float scaleHe = heightView/tv_.getHeight();
////        tv_.setScaleY(scaleHe);
//
////        camera.getParameters().getPre
////        camera.getParameters().set(Camera.ro);
//    }
//
//    @Override
//    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
//        camera = android.hardware.Camera.open(cId);
//        try {
//            configCamera();
//            camera.setPreviewTexture(surfaceTexture);
//            camera.startPreview();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
//
//    }
//
//    @Override
//    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
//        return false;
//    }
//
//    @Override
//    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.camera:
////                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////                final Date date = new Date();
////                camera.takePicture(null, null, new android.hardware.Camera.PictureCallback() {
////                    @Override
////                    public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {
////                        File file = new File(Environment.getExternalStorageDirectory().getPath()
////                                +"/IMG_"+ format.format(date)+".jpg");
////                        try {
////                            FileOutputStream out = new FileOutputStream(file);
////                            out.write(bytes);
////                            out.close();
////
////                            ExifInterface ef = new ExifInterface(file.getPath());
////                            String tage = ef.getAttribute(ExifInterface.TAG_ORIENTATION);
////                            ef.setAttribute(tage, ExifInterface.ORIENTATION_ROTATE_180+"");
////                            ef.saveAttributes();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                        camera.stopPreview();
////                        try {
////                            camera.reconnect();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                        camera.startPreview();
////                    }
////                });
////                break;
////                default: break;
//        }
//
//    }
//}
