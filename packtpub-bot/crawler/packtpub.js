const got       = require('got');
const cheerio   = require('cheerio');

const PROTOCOL = "https:";
const HOST = "https://www.packtpub.com";
const URL = "https://www.packtpub.com/packt/offers/free-learning";

function fetch(){

    return got(URL).then(function(data){

        var $ = cheerio.load(data.body,{decodeEntities:false});

        var $chunk = cheerio.load($('#deal-of-the-day').html(),{decodeEntities:false});

        var book = {
            claim : URL
        };

        $chunk('.dotd-main-book-image a').each(function(i, elem) {
            var link = elem.attribs.href;
            if(!link.startsWith(HOST)) link = HOST.concat(link);
            book.link = link;
        });

        $chunk('.dotd-main-book-image a img.bookimage').each(function(i, elem) {
            var data = elem.attribs["data-original"];
            if(typeof data !== 'undefined'){
                if(!data.startsWith(PROTOCOL)) data = PROTOCOL.concat(data);

                var parts = data.split('/');
                parts[parts.length - 1] = encodeURIComponent(parts[parts.length - 1]);

                book.thumbnail = parts.join("/");
            }
        });

        $chunk('.dotd-main-book-summary .dotd-title h2').each(function(i, elem) {
            var title = elem.children[0].data;
            title = title.replace(/\n/g, "").replace(/\t/g, "");
            book.title = title;
        });

        return book;
    });
}

module.exports = {
    fetch : fetch
};