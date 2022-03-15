package com.example.thru

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.thru.databinding.FragmentWallBinding
import kotlin.random.Random

private const val EMPTY_WIDTH = "empty_width"
private const val TOTAL_WEIGHT = 4.0

class WallFragment : Fragment()
{
    private lateinit var binding : FragmentWallBinding
    private var emptyWidth : Int = 120

    // initialize attributes when the fragment is created
    override fun onCreate( savedInstanceState: Bundle? )
    {
        super.onCreate( savedInstanceState )
        arguments?.let {
            emptyWidth = it.getInt( EMPTY_WIDTH )
        }
    }

    // bind to the fragment view when it is created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentWallBinding.inflate( inflater, container, false )
        return binding.root
    }

    // randomize the wall after the view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        // randomize the position of empty wall by altering the layout weight of left and right wall
        val w = Random.nextDouble( 1.0, 3.0 )
        ( binding.wallLeft.layoutParams as LinearLayout.LayoutParams ).weight = w.toFloat()
        ( binding.wallRight.layoutParams as LinearLayout.LayoutParams ).weight = ( TOTAL_WEIGHT - w ).toFloat()
    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment WallFragment.
         */
        @JvmStatic
        fun newInstance( emptySize: Int ) = WallFragment().apply {
            arguments = Bundle().apply {
                putInt( EMPTY_WIDTH, emptySize )
            }
        }
    }
}