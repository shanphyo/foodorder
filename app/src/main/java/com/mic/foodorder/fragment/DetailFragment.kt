package com.mic.foodorder.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mic.foodorder.Adapter.MFCAdapter
import com.mic.foodorder.Adapter.MenusAdapter
import com.mic.foodorder.Model.Menu
import com.mic.foodorder.R
import com.mic.foodorder.ViewModel.MFCViewModel
import com.mic.foodorder.ViewModel.MenusViewModel
import com.mic.foodorder.database.Card
import com.mic.foodorder.database.CardViewModel
import com.mic.webservice.Adapter.RecyclerDataMFc
import com.mic.webservice.Adapter.RecyclerDataMenu
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment(), RecyclerDataMenu,RecyclerDataMFc {
    private lateinit var mListAdapter: MenusAdapter
    private lateinit var mViewModel: MenusViewModel
    private lateinit var mfcListAdapter: MFCAdapter
    private lateinit var mfcViewModel: MFCViewModel
    private lateinit var caViewModel:CardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_detail, container, false)
        root.floatingActionButton.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_detailFragment_to_cardFragment)
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            view?.findNavController()?.navigate(R.id.action_detailFragment_to_homeFragment)
        }


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var t = arguments!!.getString("id")?.toInt()
        if(t!=null) {
            recycler_menu.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            mListAdapter = MenusAdapter(this)
            recycler_menu.adapter = mListAdapter



            if (t != null) {

                MenusobserveViewModel(t)
            }
        }
        var d=arguments!!.getString("mfc")?.toInt()
        if(d!=null){
            recycler_menu.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            mfcListAdapter= MFCAdapter(this)
            recycler_menu.adapter=mfcListAdapter
            MFCobserveViewModel(d)
        //menus from category
        }
    }
    private  fun MFCobserveViewModel(t:Int){
        mfcViewModel=ViewModelProvider(this).get(MFCViewModel::class.java)
        mfcViewModel.loadgetMenus(t)
        mfcViewModel.topmenus.observe(
            viewLifecycleOwner, Observer {
                recycler_menu.visibility=View.VISIBLE
                mfcListAdapter.updateMenuslist(it)
            }
        )
    }
    private fun MenusobserveViewModel(t:Int){
        mViewModel = ViewModelProvider(this).get(MenusViewModel::class.java)
         mViewModel.loadgetMenus(t)
        mViewModel.topmenus.observe(
            viewLifecycleOwner, Observer {
                recycler_menu.visibility=View.VISIBLE
                mListAdapter.updateMenuslist(it)
            }
        )
    }

    override fun onFunClick(vote: Menu) {
        Toast.makeText(context,"Add to Cart",Toast.LENGTH_LONG).show()
        val cardd=Card()
        cardd.cname=vote.menu_name
        cardd.cprice=vote.menu_price.toDouble()
        caViewModel=ViewModelProvider(this).get(CardViewModel::class.java)
        caViewModel.insert(cardd)
    }





}