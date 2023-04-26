package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var loginBinding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        loginBinding = FragmentLoginBinding.inflate(layoutInflater,container,false)

        //Initialize Firebase Authentication
        auth = Firebase.auth


        loginBinding.signUpBtn.setOnClickListener {
            val email = loginBinding.emailEditText.text.toString()
            val password = loginBinding.passwordEditText.text.toString()

            if(email == "" || password == ""){
                Toast.makeText(context,"E-mail və şifrə daxil edin." ,Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    val logAcc = LoginFragmentDirections.loginAccount(email,password)
                    findNavController().navigate(logAcc)
                }.addOnFailureListener{
                    Toast.makeText(context,it.localizedMessage ,Toast.LENGTH_LONG).show()
                }
            }


        }



        return loginBinding.root
    }

}