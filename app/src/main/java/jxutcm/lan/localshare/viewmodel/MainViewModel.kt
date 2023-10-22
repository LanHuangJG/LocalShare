package jxutcm.lan.localshare.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.UriUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.readUTF8Line
import io.ktor.utils.io.writeAvailable
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class MainViewModel : ViewModel() {
    private lateinit var socket: Socket
    private lateinit var sendChannel: ByteWriteChannel
    private lateinit var receiveChannel: ByteReadChannel
    private val _messageList = MutableStateFlow(emptyArray<String>())
    private val _translateState = MutableStateFlow("")
    val messageList = _messageList.asStateFlow()
    val translateState = _translateState.asStateFlow()
    fun connect() {
        val selectorManager = SelectorManager(Dispatchers.IO)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                socket = aSocket(selectorManager).tcp().connect("192.168.137.1", 8877)
                sendChannel = socket.openWriteChannel(autoFlush = true)
                receiveChannel = socket.openReadChannel()
                _translateState.value = "连接成功"
                while (true) {
                    val message = receiveChannel.readUTF8Line()
                    if (message != null) {
                        _messageList.value = _messageList.value.plus(message)
                    }
                }
            }.onFailure {
            it.printStackTrace()
        }
    }
}

fun send(message: String) {
    viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            sendChannel.writeStringUtf8(message)
        }.onFailure {
            it.printStackTrace()
        }
    }
}

fun sendImage(uri: Uri) {
    _translateState.value = "正在发送图片"
    val byteArray = UriUtils.uri2Bytes(uri)
    viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            sendChannel.writeFully(byteArray, 0, byteArray.size)
        }.onSuccess {
            _translateState.value = "图片发送成功"
        }.onFailure {
            it.printStackTrace()
        }
    }
}

fun sendFile(uri: Uri) {
    _translateState.value = "正在发送文件"
    val byteArray = UriUtils.uri2Bytes(uri)
    viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            sendChannel.writeFully(byteArray, 0, byteArray.size)
        }.onSuccess {
            _translateState.value = "文件发送成功"
        }.onFailure {
            it.printStackTrace()
        }
    }
}
}