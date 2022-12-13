package space.beka.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import space.beka.chatapp.R
import space.beka.chatapp.adapter.UserAdapter
import space.beka.chatapp.databinding.FragmentHomeBinding
import space.beka.chatapp.models.User

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var userReference: DatabaseReference
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance()
        userReference = database.getReference("users")
        userAdapter = UserAdapter()
        binding.rvUsers.adapter = userAdapter
        loadData()
        return binding.root
    }

    private fun loadData() {
        userReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                for (child in children) {
                    val value = child.getValue(User::class.java)
if (value!=null){
    userAdapter.list.add(value)
}
                }
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        })
    }
}