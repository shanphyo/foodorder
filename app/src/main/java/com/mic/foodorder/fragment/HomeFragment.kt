package com.mic.foodorder.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mic.foodorder.Adapter.AllRestAdapter
import com.mic.foodorder.Adapter.CAdapter
import com.mic.foodorder.Adapter.RestAdapter
import com.mic.foodorder.Model.CategoryX
import com.mic.foodorder.Model.RestaurantX
import com.mic.foodorder.Model.Township
import com.mic.foodorder.R
import com.mic.foodorder.ViewModel.AllRestaurantViewModel
import com.mic.foodorder.ViewModel.CategoryViewModel
import com.mic.foodorder.ViewModel.TownShipViewModel
import com.mic.foodorder.ViewModel.TownShipXViewModel
import com.mic.webservice.Adapter.RecyclerData
import com.mic.webservice.Adapter.RecyclerDataForCategory
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),RecyclerData,RecyclerDataForCategory {

    private lateinit var cListAdapter: CAdapter
    private lateinit var cViewModel: CategoryViewModel
    private lateinit var  resListAdapter:RestAdapter
    private lateinit var  resViewModel:TownShipViewModel
    private lateinit var  allresListAdapter:AllRestAdapter
    private lateinit var allresViewModel:AllRestaurantViewModel

    private var townshipArray: List<Township> = ArrayList()

    lateinit var townshipxViewmodel: TownShipXViewModel
    lateinit var spinner:Spinner
    var s: String? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_home, container, false)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  recycler_category.layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        recycler_category.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        cListAdapter = CAdapter(this)
        recycler_category.adapter=cListAdapter
        observeViewModel()

        townshipSpinner()




       /* //township
        recycler_township.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        resListAdapter = RestAdapter(this)
        recycler_township.adapter=resListAdapter
       townshipSpinner()
        val ts="tamwe"
        townshipObserveViewModel(ts)*/

        //Alltownship
        recycler_allres.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        allresListAdapter= AllRestAdapter(this)
        recycler_allres.adapter=allresListAdapter
        allRestaurantObserveViewModel()
    }
    private fun allRestaurantObserveViewModel(){
        allresViewModel =ViewModelProvider(this).get(AllRestaurantViewModel::class.java)
        allresViewModel.loadAllRestaurantbytownship()
        allresViewModel.resta.observe(
            viewLifecycleOwner, Observer {
                recycler_allres.visibility=View.VISIBLE
                allresListAdapter.updateRestaurantlist(it)
            }
        )

    }
    private fun townshipObserveViewModel(ts:String){
       resViewModel =ViewModelProvider(this).get(TownShipViewModel::class.java)

        resViewModel.loadRestaurantbytownship(ts)
        resViewModel.resta.observe(
            viewLifecycleOwner, Observer {
                recycler_township.visibility=View.VISIBLE
                resListAdapter.updateRestaurantlist(it)
            }
        )

    }
    private fun townshipSpinner(){
        townshipxViewmodel=ViewModelProvider(this).get(TownShipXViewModel::class.java)
        townshipxViewmodel.loadTownship()
        townshipxViewmodel.getTownship().observe(
            viewLifecycleOwner, Observer {
                    result ->
                townshipArray = result.townships
                var data: MutableList<String> = ArrayList()

                townshipArray.forEach {
                    data.add(0, it.township_name)
                    sp_township.adapter = context?.let {
                        ArrayAdapter<String>(
                            it,
                            R.layout.support_simple_spinner_dropdown_item,
                            data
                        )
                    }
                }
            }
        )
      sp_township.onItemSelectedListener=object :AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{
          override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              Toast.makeText(context,"Nothing",Toast.LENGTH_LONG).show()
          }

          override fun onNothingSelected(parent: AdapterView<*>?) {
              TODO("Not yet implemented")
          }

          override fun onItemSelected(
              parent: AdapterView<*>?,
              view: View?,
              position: Int,
              id: Long
          ) {

              s=sp_township.selectedItem.toString()
              /*var ts:String="tamwe"
              s?.let { getTownshipRestaurant(it) }*/
              getTownshipRestaurant(s!!)
          }

      }
    }

    private  fun observeViewModel(){
        cViewModel = ViewModelProvider(this)
            .get(CategoryViewModel::class.java)
        cViewModel.getloadCategory()
        cViewModel.getCategory.observe(
            viewLifecycleOwner, Observer {
                recycler_category.visibility = View.VISIBLE
                cListAdapter.updateResultlist(it)
            }
        )
    }

    override fun onFunClick(vote: RestaurantX) {

   val txtmessage=Bundle()
       txtmessage.putString("id",vote.user.id.toString())
       view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment,txtmessage)
    }

    override fun onFunClick(vote: CategoryX) {
        val txtmessage=Bundle()
        txtmessage.putString("mfc",vote.id.toString())
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment,txtmessage)
    }
    fun getTownshipRestaurant(ts:String){
        //township
        recycler_township.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        resListAdapter = RestAdapter(this)
        recycler_township.adapter=resListAdapter


        townshipObserveViewModel(ts)
    }



}