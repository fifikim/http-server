require "./features/steps/shared.rb"

class Spinach::Features::StructuredData < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a GET request to "/text_response"' do
    @response = Requests.get("/text_response")
  end

  step 'my response should have a text body' do
    expect(@response.body).to eq "text response"
  end

  step 'I make a GET request to "/html_response"' do
    @response = Requests.get("/html_response")
  end

  step 'my response should have an html body' do
    expect(@response.body).to eq "<html><body><p>HTML Response</p></body></html>"
  end

  step 'I make a GET request to "/json_response"' do
    @response = Requests.get("/json_response")
  end

  step 'my response should have a JSON body' do
    expect(@response.body).to eq({ key1: 'value1', key2: 'value2' }.to_json)
  end

  step 'I make a GET request to "/xml_response"' do
    @response = Requests.get("/xml_response")
  end

  step 'my response should have an XML body' do
    expect(@response.body).to eq("<note><body>XML Response</body></note>")
  end
end
