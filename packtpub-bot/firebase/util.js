const firebase = require("firebase-admin");

var serviceAccount = require("../firebase/firebase-connection.json");

firebase.initializeApp({
    credential: firebase.credential.cert(serviceAccount),
    databaseURL: 'YOUR_FIREBASE_DATABASE_URL'
});

var connection = firebase.database();

function saveChat(chat_id, person){
    connection.ref('/chats/' + chat_id).set(person);
}

function getChatsRF(){
    return connection.ref('/chats');
}

module.exports = {

    getChatsRF : getChatsRF,

    saveChat : saveChat
};