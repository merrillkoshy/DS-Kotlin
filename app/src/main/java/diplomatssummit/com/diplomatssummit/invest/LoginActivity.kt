package diplomatssummit.com.diplomatssummit.invest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import com.squareup.picasso.Picasso
import android.widget.ImageView
import android.widget.Toast
import diplomatssummit.com.diplomatssummit.MainActivity
import diplomatssummit.com.diplomatssummit.R

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(){
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        email_sign_in_button.setOnClickListener { attemptLogin() }
        val iv:ImageView=findViewById(R.id.register_prompt)
        Picasso.get().load("https://diplomatssummit.com/Registration_assets/join.jpg").centerCrop().fit().into(iv)
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        uname.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val unameStr = uname.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null


        // Check for  uname
        if (TextUtils.isEmpty(unameStr)) {
            uname.error = getString(R.string.error_field_required)
            focusView = uname
            cancel = true
        }

        // Check for  uname
        if (TextUtils.isEmpty(passwordStr)) {
            password.error = getString(R.string.error_field_required)
            focusView = uname
            cancel = true
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

           UserLoginTask(unameStr, passwordStr)
        }
    }

    fun UserLoginTask(unameStr: String, passwordStr: String) {
        val uname=DUMMY_CREDENTIALS.get(0)
        val pw= DUMMY_CREDENTIALS.get(1)
        if (unameStr==uname && passwordStr==pw)
        {
            val intent:Intent=Intent(this,Authorised::class.java)
            intent.putExtra("userName",unameStr)
            intent.putExtra("passWord",passwordStr)
            startActivity(intent)
        }
        else{
            incorrect_login.visibility=View.VISIBLE
            incorrect_login.text="Incorrect Username or Password"
            val clr=ContextCompat.getColor(this, R.color.colorPrimary)
            incorrect_login.setTextColor(clr)
        }


    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("admin", "5reaK!@#")
    }
}
