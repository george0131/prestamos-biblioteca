(function () {
    'use strict';

    window.lend = (function (lend) {

        lend.new = (function ($, newLend) {

            newLend.initializeComponents = function () {

                $('.select-two').select2({
                    width: '100%'
                });

                $("#reader_docId").on('input', newLend.getInfoReader);
            }

            newLend.getInfoReader = function () {

                let value = $(this).val();

                if (value.length >= 7 && value.length <= 10) {
                    $.ajax({
                        url: '/api/reader-info/' + parseInt(value),
                        method: "GET",
                        success: function (response) {
                            if (response !== undefined && response !== null) {
                                $("#reader_names").val(response.names);
                                $("#reader_lastnames").val(response.lastNames);
                                $("#reader_birthdate").val(response.birthDate);
                                $("#reader_cellphone").val(response.cellPhone);
                            } else {
                                $("#reader_names").val("");
                                $("#reader_lastnames").val("");
                                $("#reader_birthdate").val("");
                                $("#reader_cellphone").val("");
                            }
                        },
                        error: function () {
                            $("#reader_names").val("");
                            $("#reader_lastnames").val("");
                            $("#reader_birthdate").val("");
                            $("#reader_cellphone").val("");
                        }
                    });
                }

            }

            $(newLend.initializeComponents);

            return newLend;

        })(window.jQuery, lend.new || {});

        return lend;

    }(window.lend || {}));

})();