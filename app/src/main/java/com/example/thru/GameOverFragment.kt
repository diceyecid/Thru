package com.example.thru

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thru.databinding.FragmentGameOverBinding

private const val YOUR_SCORE = "Your Score"
private const val BEST_SCORE = "Best Score"

class GameOverFragment : Fragment()
{
    private lateinit var binding : FragmentGameOverBinding
    private var yourScore: Int = 0
    private var bestScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            yourScore = it.getInt( YOUR_SCORE )
            bestScore = it.getInt( BEST_SCORE )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentGameOverBinding.inflate( inflater, container, false )

        // interactive components
        binding.yourScoreValue.text = yourScore.toString()
        binding.bestScoreValue.text = bestScore.toString()
        binding.againButton.setOnClickListener{
            val intent = Intent( context, GameActivity::class.java )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity( intent )
        }
        binding.homeButton.setOnClickListener{
            val intent = Intent( context, HomeActivity::class.java )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity( intent )
        }

        return binding.root
    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param yourScore
         * @param bestScore
         * @return A new instance of fragment GameOverFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance( yourScore : Int, bestScore : Int ) = GameOverFragment().apply {
            arguments = Bundle().apply {
                putInt( YOUR_SCORE, yourScore )
                putInt( BEST_SCORE, bestScore )
            }
        }
    }
}