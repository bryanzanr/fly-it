package com.arsyady.flyit.merchant.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsyady.flyit.merchant.R

/**
 * A placeholder fragment containing a simple view.
 */
class MerchantFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_merchant, container, false)
    }
}
