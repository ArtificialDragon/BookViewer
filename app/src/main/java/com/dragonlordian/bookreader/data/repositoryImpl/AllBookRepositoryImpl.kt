package com.dragonlordian.bookreader.data.repositoryImpl

import android.util.Log
import com.dragonlordian.bookreader.data.model.BookCategory
import com.dragonlordian.bookreader.data.model.BookModel
import com.dragonlordian.bookreader.data.model.ResultState
import com.dragonlordian.bookreader.domain.repository.AllBookRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepositoryImpl @Inject constructor(
    val firebaseDatabase: FirebaseDatabase
) : AllBookRepository {

    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        Log.d("DFIREBASE", "Loading sent in getAllBooks")

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                items = snapshot.children.map { value ->
                    value.getValue<BookModel>()!!
                }

                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllCategory(): Flow<ResultState<List<BookCategory>>> = callbackFlow {
        trySend(ResultState.Loading)
        Log.d("DFIREBASE", "Loading sent in getAllCategory")

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookCategory> = emptyList()
                items = snapshot.children.map { value ->
                    Log.d("DFIREBASE", "Mapping values with model in getAllCategory")
                    value.getValue<BookCategory>()!!
                }

                trySend(ResultState.Success(items))
                Log.d("DFIREBASE", "Sent Success in getAllCategory")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DFIREBASE", "Canceled invoked in getAllCategory")
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("BooksCategory").addValueEventListener(valueEvent)
        awaitClose {
            Log.d("DFIREBASE", "Closing firebase in getAllCategory")
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        Log.d("DFIREBASE", "Loading sent in getAllBooksByCategory")

        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                items = snapshot.children.filter { value ->
                    value.getValue<BookModel>()!!.category == category
                }.map { value ->
                    value.getValue<BookModel>()!!
                }

                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
        }
    }
}