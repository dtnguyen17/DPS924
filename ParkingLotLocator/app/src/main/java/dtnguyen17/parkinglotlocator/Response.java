package dtnguyen17.parkinglotlocator;

import java.util.List;

public class Response {
    /**
     * id : 1
     * address : 20 Charles Street
     * lat : 43.668997
     * lng : -79.385093
     * rate : $2.00 / Half Hour
     * carpark_type : garage
     * carpark_type_str : Garage
     * is_ttc : false
     * rate_half_hour : 2.00
     * capacity : 430
     * max_height : 2.00
     * payment_methods : ["Bills","Coins","Charge (Visa / Mastercard / American Express Only)"]
     * payment_options : ["Auto Express Pay Stations","Credit Card at Entry & Exit","Customer Assistance Booth"]
     * rate_details : {"periods":[{"title":"Monday - Sunday & Holidays","rates":[{"when":"Day Maximum (7am - 6pm)","rate":"$12.00"},{"when":"Night Maximum (6pm - 7am)","rate":"$6.00"}],"notes":["Unreserved Monthly permit price - $220","Permits currently available","Inquires at monthlypermits@greenp.com or","416-393-7348"]}],"addenda":[]}
     * enable_streetview : yes
     * streetview_lat : 43.668822
     * streetview_long : -79.385262
     * streetview_yaw : 321.21
     * streetview_pitch : -12.45
     * streetview_zoom : 0
     */

    private List<CarparksEntity> carparks;

    public void setCarparks(List<CarparksEntity> carparks) {
        this.carparks = carparks;
    }

    public List<CarparksEntity> getCarparks() {
        return carparks;
    }

    public static class CarparksEntity {
        private String id;
        private String address;
        private String lat;
        private String lng;
        private String rate;
        private String carpark_type_str;
        private String capacity;
        private RateDetailsEntity rate_details;
        private List<String> payment_methods;
        private List<String> payment_options;

        public void setId(String id) {
            this.id = id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public void setCarpark_type_str(String carpark_type_str) {
            this.carpark_type_str = carpark_type_str;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public void setRate_details(RateDetailsEntity rate_details) {
            this.rate_details = rate_details;
        }

        public void setPayment_methods(List<String> payment_methods) {
            this.payment_methods = payment_methods;
        }

        public void setPayment_options(List<String> payment_options) {
            this.payment_options = payment_options;
        }

        public String getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getRate() {
            return rate;
        }

        public String getCarpark_type_str() {
            return carpark_type_str;
        }

        public String getCapacity() {
            return capacity;
        }

        public RateDetailsEntity getRate_details() {
            return rate_details;
        }

        public List<String> getPayment_methods() {
            return payment_methods;
        }

        public List<String> getPayment_options() {
            return payment_options;
        }

        public static class RateDetailsEntity {
            /**
             * title : Monday - Sunday & Holidays
             * rates : [{"when":"Day Maximum (7am - 6pm)","rate":"$12.00"},{"when":"Night Maximum (6pm - 7am)","rate":"$6.00"}]
             * notes : ["Unreserved Monthly permit price - $220","Permits currently available","Inquires at monthlypermits@greenp.com or","416-393-7348"]
             */

            private List<PeriodsEntity> periods;
            private List<?> addenda;

            public void setPeriods(List<PeriodsEntity> periods) {
                this.periods = periods;
            }

            public void setAddenda(List<?> addenda) {
                this.addenda = addenda;
            }

            public List<PeriodsEntity> getPeriods() {
                return periods;
            }

            public List<?> getAddenda() {
                return addenda;
            }

            public static class PeriodsEntity {
                private String title;
                /**
                 * when : Day Maximum (7am - 6pm)
                 * rate : $12.00
                 */

                private List<RatesEntity> rates;
                private List<String> notes;

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setRates(List<RatesEntity> rates) {
                    this.rates = rates;
                }

                public void setNotes(List<String> notes) {
                    this.notes = notes;
                }

                public String getTitle() {
                    return title;
                }

                public List<RatesEntity> getRates() {
                    return rates;
                }

                public List<String> getNotes() {
                    return notes;
                }

                public static class RatesEntity {
                    private String when;
                    private String rate;

                    public void setWhen(String when) {
                        this.when = when;
                    }

                    public void setRate(String rate) {
                        this.rate = rate;
                    }

                    public String getWhen() {
                        return when;
                    }

                    public String getRate() {
                        return rate;
                    }
                }
            }
        }
    }
}
