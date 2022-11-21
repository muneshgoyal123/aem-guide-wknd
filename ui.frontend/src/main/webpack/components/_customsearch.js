(function () {
  "use strict";
  var selectors = {
    self: '[data-cmp-is="customsearch"]',
  };

  var searchPath = $(".cmp-customsearch__field").attr(
    "data-cmp-customsearch-path"
  );
  var pageIndex = 0;
  var searchLimit = $(".cmp-customsearch__field").attr(
    "data-cmp-customsearch-limit"
  );
  const cropParameter = "?crop=200,0,800,800&wid=800&hei=800&qlt=60";
  var searchType = "customsearch";

  function SiteSearch(config) {
    function getResults(fulltext) {
      $.ajax({
        type: "GET",
        url: "/services/customsearch?" + $.param({ query: fulltext }),
        data:
          "searchType=" +
          searchType +
          "&searchPath=" +
          searchPath +
          "&limit=" +
          searchLimit,
        success: function (msg) {
          console.log(msg);
          var json = jQuery.parseJSON(msg);
          $("#json").val(msg);

          var template = `<div class="cmp-customsearch__results-item">
                          <img class="cmp-customsearch__results-image" {{hideImage}} src="{{imagePath}}">
                          <a class="cmp-customsearch__results-link" href="{{path}}">
                              <h3>{{title}}</h3>
                          </a>
                          <div class="cmp-customsearch__results-link">{{description}}</div>
                      </div>`;

          var markup = "";
          for (var i = 0; i < Object.keys(json).length; i++) {
            var itemMarkup = template;
            itemMarkup = itemMarkup.replace("{{title}}", json[i].title);
            itemMarkup = itemMarkup.replace("{{path}}", json[i].path + ".html");
            itemMarkup = itemMarkup.replace(
              "{{description}}",
              json[i].description
            );
            if (json[i].type == "cq:Page") {
              itemMarkup = itemMarkup.replace("{{hideImage}}", "hidden");
            } else if (json[i].type == "dam:Asset") {
              itemMarkup = itemMarkup.replace(
                "{{imagePath}}",
                json[i].path + cropParameter
              );
            }
            markup = markup + itemMarkup;
          }
          $(".cmp-customsearch__results").html(markup);
        },
      });
    }

    function init(config) {
      config.element.removeAttribute("data-cmp-is");
      var fulltext = $(".cmp-customsearch__input").val();
      if (fulltext == null || fulltext == "") {
        fulltext = window.location.search.split("=")[1];
        $(".cmp-customsearch__input input").val(fulltext);
      }
      if (fulltext != null && fulltext != "") {
        getResults(fulltext);
        window.history.replaceState({}, "", "?query=" + fulltext);
      }
    }

    if (config && config.element) {
      init(config);
      $(".cmp-customsearch__search button").on("click", function () {
        init(config);
      });
    }
  }

  function bindComponents() {
    var elements = document.querySelectorAll(selectors.self);
    for (var i = 0; i < elements.length; i++) {
      new SiteSearch({ element: elements[i] });
    }

    var MutationObserver =
      window.MutationObserver ||
      window.WebKitMutationObserver ||
      window.MozMutationObserver;
    var body = document.querySelector("body");
    var observer = new MutationObserver(function (mutations) {
      mutations.forEach(function (mutation) {
        // needed for IE
        var nodesArray = [].slice.call(mutation.addedNodes);
        if (nodesArray.length > 0) {
          nodesArray.forEach(function (addedNode) {
            if (addedNode.querySelectorAll) {
              var elementsArray = [].slice.call(
                addedNode.querySelectorAll(selectors.self)
              );
              elementsArray.forEach(function (element) {
                new SiteSearch({ element: element });
              });
            }
          });
        }
      });
    });

    observer.observe(body, {
      subtree: true,
      childList: true,
      characterData: true,
    });
  }

  function onDocumentReady() {
    var waitForJquery = setInterval(function () {
      if (typeof $ !== "undefined") {
        clearInterval(waitForJquery);
        bindComponents();
      }
    }, 10);
  }

  if (document.readyState !== "loading") {
    onDocumentReady();
  } else {
    document.addEventListener("DOMContentLoaded", onDocumentReady);
  }
})();
