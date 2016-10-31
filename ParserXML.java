private Hashtable <Integer, Question> insertQuestionsFromResource (int resource, Hashtable <Integer, Question> table) throws IOException, XmlPullParserException {
        XmlResourceParser parsRes = getResources().getXml(resource);
        int eventType = parsRes.getEventType();
        //boolean bFoundScores = false;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                if (parsRes.getName().equals(XML_TAG_QUESTION)) {
                    //bFoundScores = true;
                    boolean answer;
                    Integer questionNum = Integer.valueOf(parsRes.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_NUMBER));
                    String questionText = parsRes.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_TEXT);
                    String questionAnswer = parsRes.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_ANSWER);
                    String questionImageUri = parsRes.getAttributeValue(null, XML_TAG_QUESTION_ATTRIBUTE_IMAGE_URL);
                    answer = !questionAnswer.equals("0");
                    table.put(questionNum, new Question(questionNum, questionText, answer, questionImageUri));
                }
            }
            eventType = parsRes.next();
        }
        return table;
    }