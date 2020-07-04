package com.mic.foodorder.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mic.foodorder.Adapter.CardAdapter
import com.mic.foodorder.R
import com.mic.foodorder.database.Card
import com.mic.foodorder.database.CardViewModel
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_card.view.*
import kotlinx.android.synthetic.main.fragment_card.view.btn_cancel


class CardFragment : Fragment() {
    private  lateinit var cdViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      var root=inflater.inflate(R.layout.fragment_card, container, false)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            view?.findNavController()?.navigate(R.id.action_cardFragment_to_detailFragment)
        }
        val cdAdapter=CardAdapter()
       root.btn_cancel.setOnClickListener {
           cdViewModel.getDelete()
           view?.findNavController()?.navigate(R.id.action_cardFragment_to_homeFragment)
        }
        root.btn_Order.setOnClickListener {
            view?.let { it1 -> Snackbar.make(it1,"Thank You! Your Order is Submitted",Snackbar.LENGTH_LONG).show() }
            cdViewModel.getDelete()
            view?.findNavController()?.navigate(R.id.action_cardFragment_to_homeFragment)
        }
        cdViewModel=ViewModelProvider(this).get(CardViewModel::class.java)

        cdViewModel.allCard.observe(
            viewLifecycleOwner, Observer {
                cdlist->cdlist?.let { cdAdapter.setCard(it)

            }
                var d=  cdViewModel.getTotal()
               root.cv_totalvalue.text=d.toString()


            }
        )

        root.care_recycler.layoutManager=LinearLayoutManager(root.context)
        root.care_recycler.adapter=cdAdapter
        return root
    }
}