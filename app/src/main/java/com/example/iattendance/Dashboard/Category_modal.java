    package com.example.iattendance.Dashboard;

    import java.util.List;

    public class Category_modal {
        String category, subj_count;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSubj_count() {
            return subj_count;
        }

        public void setSubj_count(String subj_count) {
            this.subj_count = subj_count;
        }

        public Category_modal(String category, String subj_count) {
            this.category = category;
            this.subj_count = subj_count;
        }
    }
