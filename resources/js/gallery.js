window.togglePhoto = photo => {
    const photoExposed = $(photo).hasClass("photo-exposed");

    $(".photo").removeClass("photo-exposed");

    if (photoExposed) $(photo).removeClass("photo-exposed");
    else $(photo).addClass("photo-exposed");
}