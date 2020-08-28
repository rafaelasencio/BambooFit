package com.rafaelab.bamboofitapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_confirmation_finish_exercise.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import com.bumptech.glide.Glide;

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    //Variables
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration: Long = 1//0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration: Long = 5//30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var player: MediaPlayer? = null

    private var tts: TextToSpeech? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null

    var levelChoose = 1

    var timeLeft: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)

        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_exercise_activity.setNavigationOnClickListener {
            //onBackPressed()
            customDialogForBackButton()
        }

        //Obtener nivel elegido por usuario en activity anterior
        val levelChoose = intent.getIntExtra("level_key",levelChoose)

        //Inicializar la lista de ejercicios
        when(levelChoose){
            0 -> exerciseList = Constants.easyExerciseList()
            1 -> exerciseList = Constants.mediumExerciseList()
            2 -> exerciseList = Constants.advancedExerciseList()
        }

        setupRestView()

        tts = TextToSpeech(this, this)
        setupExerciseStatusRecyclerView()
    }

    //Resetear los valores para el ProgressBar de descanso
    override fun onDestroy() {
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (player != null){
            player!!.stop()
        }

        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }




    //Establecer los valores de la barra de progreso en modo descanso
    private fun setRestProgressBar(withTime: Long){
        progressBar.progress = restProgress

        //Decrementar el valor del tvTimer por cada cada segundo
        restTimer = object : CountDownTimer(withTime * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                timeLeft = withTime - restProgress
                progressBar.progress = (withTime - restProgress).toInt()
                tvTimer.text = (withTime - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }
        }.start()
    }

    //Preparar la Activity para el modo de descanso
    private fun setupRestView(){
        try {
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false
            player!!.start()
        }catch (e: Exception){
            e.printStackTrace()
        }

        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE

        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar(withTime = restTimerDuration)
    }

    //Establecer los valores de la barra de progreso en modo de ejercicio
    private fun setExerciseProgressBar(withTime: Long){
        progressBarExercise.progress = exerciseProgress

        //Decrementar el valor del tvTimer por cada cada segundo
        exerciseTimer = object : CountDownTimer(withTime * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                timeLeft = withTime - exerciseProgress
                progressBarExercise.progress = withTime.toInt() - exerciseProgress
                tvExerciseTimer.text = (withTime.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList?.size!! - 1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    //Preparar la Activity para el modo de ejercicio
    private fun setupExerciseView(){

        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        speakText(exerciseList!![currentExercisePosition].getName())
        setExerciseProgressBar(withTime = exerciseTimerDuration)

        Glide.with(this).load(exerciseList!![currentExercisePosition].getImage()).into(ivImage)

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "El idioma indicado no es compatible")
                Toast.makeText(this@ExerciseActivity, "El idioma no es compatible", Toast.LENGTH_SHORT).show()
            }
        }else{
            Log.e("TTS", "Inicialización fallida")
        }
    }

    private fun speakText(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    private fun setupExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)

        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun customDialogForBackButton() {
        if(restTimer!=null) {
            restTimer!!.cancel()
        }
        if(exerciseTimer!=null) {
            exerciseTimer!!.cancel()
        }

        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_confirmation_finish_exercise)

        customDialog.tvConfirm.setOnClickListener {
            finish()
            customDialog.dismiss()
        }

        customDialog.tvCancel.setOnClickListener {
            resumeTimer()
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun resumeTimer(){
        if(restTimer!=null){
            restProgress = 0
            setRestProgressBar(withTime = timeLeft)
        }
        if(exerciseTimer!=null){
            exerciseProgress = 0
            setExerciseProgressBar(withTime = timeLeft)
        }
    }




}
