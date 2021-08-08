package com.gst.example.domain.repository

import com.gst.example.domain.model.Account
import com.gst.example.domain.model.Group

interface AccountRepository {

    fun getAccountById(accountId: String): Account

    fun getAccountsInNote(noteId: String): List<Account>

    fun getGroupsInNote(noteId: String): List<Group>

    fun saveAccount(account: Account): Boolean

    fun getMyAccount(): Account
}