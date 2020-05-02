package app.ejemplo.libretadehimnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libretadehimnos.R;

import java.io.IOException;

public class Reproductor extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private TextView tex1, tex2, tex3;
    private Button b1, b2, b3, b4, b5,tod;
    private SeekBar sB;
    private MediaPlayer vectormp[] = new MediaPlayer[11];
    private Handler myHandler = new Handler();
    private double starTime = 0;
    private double finalTime = 0;
    String him[] = {"ANGELES BLANCOS", "ALLA EN EL CIELO", "CONSEJO DIVINO", "CUANTO DOLOR", "DIVINO COMPAÑERO", "LUZ DE LA MAÑANA", "MUY PRONTO VENDRA", "REGRESA","UN DIA DE BODAS", "JUVENTUD", "YO SOLO ESPERO"};
    int repetir = 0, posicion = 0,contp=0,conta=0,a=0,sound;
    public static int oneTimeOnly = 0;

    private final static String TAG = "MediaPlayer audios";
    private MediaPlayer mediaPlayer;
    private int[] sounds = {R.raw.angelesblancos, R.raw.allaenelcielo, R.raw.consejodivino,R.raw.cuantodolor,R.raw.divinocompanero,R.raw.luzdelamanana,R.raw.muyprontovendra,R.raw.regresa,R.raw.undiadebodas,R.raw.juventud,R.raw.yosoloespero};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        b1 = (Button) findViewById(R.id.boton);
        b2 = (Button) findViewById(R.id.boton2);
        b3 = (Button) findViewById(R.id.boton3);
        b4 = (Button) findViewById(R.id.boton4);
        b5 = (Button) findViewById(R.id.boton5);
        tod = (Button) findViewById(R.id.todo);
        tex1 = (TextView) findViewById(R.id.textView);
        tex1.setText(him[posicion]);
        tex1.setSelected(true);
        vectormp[0] = MediaPlayer.create(this, R.raw.angelesblancos);
        vectormp[1] = MediaPlayer.create(this, R.raw.allaenelcielo);
        vectormp[2] = MediaPlayer.create(this, R.raw.consejodivino);
        vectormp[3] = MediaPlayer.create(this, R.raw.cuantodolor);
        vectormp[4] = MediaPlayer.create(this, R.raw.divinocompanero);
        vectormp[5] = MediaPlayer.create(this, R.raw.luzdelamanana);
        vectormp[6] = MediaPlayer.create(this, R.raw.muyprontovendra);
        vectormp[7] = MediaPlayer.create(this, R.raw.regresa);
        vectormp[8] = MediaPlayer.create(this, R.raw.undiadebodas);
        vectormp[9] = MediaPlayer.create(this, R.raw.juventud);
        vectormp[10] = MediaPlayer.create(this, R.raw.yosoloespero);

        b1.setBackgroundResource(R.drawable.play);
        b2.setBackgroundResource(R.drawable.stop);
        b3.setBackgroundResource(R.drawable.repetir);
        b4.setBackgroundResource(R.drawable.ant);
        b5.setBackgroundResource(R.drawable.sig);

        mediaPlayer  = mediaPlayer.create(this, sounds[0]);
        mediaPlayer.setOnCompletionListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conta==0){
                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].pause();
                        b1.setBackgroundResource(R.drawable.play);
                        contp=0;
                        Toast.makeText(getApplication(), "PAUSA", Toast.LENGTH_SHORT).show();
                    }else {
                        vectormp[posicion].start();
                        b1.setBackgroundResource(R.drawable.pausa);
                        contp=1;
                        Toast.makeText(getApplication(), "PLAY", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vectormp[posicion] != null) {
                    vectormp[posicion].stop();
                    vectormp[0] = MediaPlayer.create(getApplication(), R.raw.angelesblancos);
                    vectormp[1] = MediaPlayer.create(getApplication(), R.raw.allaenelcielo);
                    vectormp[2] = MediaPlayer.create(getApplication(), R.raw.consejodivino);
                    vectormp[3] = MediaPlayer.create(getApplication(), R.raw.cuantodolor);
                    vectormp[4] = MediaPlayer.create(getApplication(), R.raw.divinocompanero);
                    vectormp[5] = MediaPlayer.create(getApplication(), R.raw.luzdelamanana);
                    vectormp[6] = MediaPlayer.create(getApplication(), R.raw.muyprontovendra);
                    vectormp[7] = MediaPlayer.create(getApplication(), R.raw.regresa);
                    vectormp[8] = MediaPlayer.create(getApplication(), R.raw.undiadebodas);
                    vectormp[9] = MediaPlayer.create(getApplication(), R.raw.juventud);
                    vectormp[10] = MediaPlayer.create(getApplication(), R.raw.yosoloespero);
                    posicion = 0;
                    Toast.makeText(getApplication(), "STOP", Toast.LENGTH_SHORT).show();
                    b1.setBackgroundResource(R.drawable.play);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repetir == 1) {
                    Toast.makeText(getApplication(), "NO REPETIR", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundResource(R.drawable.repetir);
                    vectormp[posicion].setLooping(false);
                    repetir = 2;
                } else {
                    b3.setBackgroundResource(R.drawable.norepetir);
                    Toast.makeText(getApplication(), "REPETIR", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundResource(R.drawable.norepetir);
                    vectormp[posicion].setLooping(true);
                    repetir = 1;
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicion >= 1) {
                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop();
                        vectormp[0] = MediaPlayer.create(getApplication(), R.raw.angelesblancos);
                        vectormp[1] = MediaPlayer.create(getApplication(), R.raw.allaenelcielo);
                        vectormp[2] = MediaPlayer.create(getApplication(), R.raw.consejodivino);
                        vectormp[3] = MediaPlayer.create(getApplication(), R.raw.cuantodolor);
                        vectormp[4] = MediaPlayer.create(getApplication(), R.raw.divinocompanero);
                        vectormp[5] = MediaPlayer.create(getApplication(), R.raw.luzdelamanana);
                        vectormp[6] = MediaPlayer.create(getApplication(), R.raw.muyprontovendra);
                        vectormp[7] = MediaPlayer.create(getApplication(), R.raw.regresa);
                        vectormp[8] = MediaPlayer.create(getApplication(), R.raw.undiadebodas);
                        vectormp[9] = MediaPlayer.create(getApplication(), R.raw.juventud);
                        vectormp[10] = MediaPlayer.create(getApplication(), R.raw.yosoloespero);
                        posicion--;
                        vectormp[posicion].start();
                        tex1.setText(him[posicion]);
                    } else {
                        posicion--;
                    }
                } else {
                    Toast.makeText(getApplication(), "NO HAY MAS HIMNOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicion < vectormp.length - 1) {
                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop();
                        posicion++;
                        vectormp[posicion].start();
                        tex1.setText(him[posicion]);
                    } else {
                        posicion++;
                    }
                } else {
                    Toast.makeText(getApplication(), "NO HAY MAS HIMNOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contp==0){
                    if (sounds.length > a){
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            conta=0;
                            Toast.makeText(getApplication(),"Stop ",Toast.LENGTH_SHORT).show();
                        }else {
                            mediaPlayer.start();
                            tex1.setText(him[posicion]);
                            conta=1;
                            Toast.makeText(getApplication(),"Se reproducira todo",Toast.LENGTH_SHORT).show();
                            a++;
                        }
                    } else {
                        Toast.makeText(getApplication(),"Fin",Toast.LENGTH_SHORT).show();
                        conta=0;
                        mediaPlayer.stop();
                    }
                 }else {
                    Toast.makeText(getApplication(),"Play",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void play(){
        sound++;
        if (sounds.length <= sound){
            //Termina reproducción de todos los audios.
            return;
        }
        AssetFileDescriptor afd = this.getResources().openRawResourceFd(sounds[sound]);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            tex1.setText(him[sound]);
            afd.close();
        }
        catch (IllegalArgumentException e) {
            Log.e(TAG, "IllegalArgumentException Unable to play audio : " + e.getMessage());
        }
        catch (IllegalStateException e) {
            Log.e(TAG, "IllegalStateException Unable to play audio : " + e.getMessage());
        }
        catch (IOException e) {
            Log.e(TAG, "IOException Unable to play audio : " + e.getMessage());
        }
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        play();
    }
    //codigo detener musica al pulsar regresar
    public void onBackPressed() {
        mediaPlayer.stop();
        for (posicion = 0; posicion < 10; posicion++) {
            if (vectormp[posicion] != null && vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
            }
        }
        super.onBackPressed();
    }


}
