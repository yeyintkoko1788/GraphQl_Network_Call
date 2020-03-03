package com.yeyint.graphqlnetworkcall.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_pin.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutRes())
        setUpContents(savedInstanceState)
    }

    abstract fun getlayoutRes(): Int

    abstract fun setUpContents(savedInstanceState: Bundle?)

    protected fun setUpToolbar(isChild: Boolean) {
        if (toolbar != null)
            setSupportActionBar(toolbar)

        if (isChild) {
            if (supportActionBar != null) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    protected fun setUpToolbarText(textValue: String) {
        toolbar_text.text = textValue
    }

    protected fun setUpToolbarIcon(icon: Int) {
        toolbar.setNavigationIcon(icon)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}