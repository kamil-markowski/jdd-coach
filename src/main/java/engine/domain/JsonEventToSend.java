package engine.domain;

import java.util.Objects;

public class JsonEventToSend {

        private String id;

        private String ip;

        private String eventName;

        private String coachInfoLink;

        private String eventDate;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getCoachInfoLink() {
            return coachInfoLink;
        }

        public void setCoachInfoLink(String coachInfoLink) {
            this.coachInfoLink = coachInfoLink;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    "ip='" + ip + '\'' +
                    "eventName='" + eventName + '\'' +
                    "coachInfoLink='" + coachInfoLink + '\'' +
                    "eventDate='" + eventDate + '\'' +
                    "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            JsonEventToSend jsonEventToSend = (JsonEventToSend) o;
            return Objects.equals(id, jsonEventToSend.id) &&
                    Objects.equals(ip, jsonEventToSend.ip) &&
                    Objects.equals(eventName, jsonEventToSend.eventName) &&
                    Objects.equals(coachInfoLink, jsonEventToSend.coachInfoLink) &&
                    Objects.equals(eventDate, jsonEventToSend.eventDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id,ip,eventName,eventDate,coachInfoLink);
        }
    }


