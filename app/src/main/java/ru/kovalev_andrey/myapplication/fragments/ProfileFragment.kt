package ru.kovalev_andrey.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import ru.kovalev_andrey.myapplication.*

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<ImageButton>(R.id.hamburgImage)?.setOnClickListener{
            val intent = Intent(activity?.applicationContext, MenuActivity::class.java)
            startActivity(intent)
        }

        activity?.findViewById<ImageButton>(R.id.imageView3)?.setOnClickListener{
            val intent = Intent(activity?.applicationContext, PhotoActivity::class.java)
            startActivity(intent)
        }

        activity?.findViewById<Button>(R.id.exitButton)?.setOnClickListener{
            val intent = Intent(activity?.applicationContext, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}