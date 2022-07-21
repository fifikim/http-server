require "./features/steps/shared.rb"

class Spinach::Features::SimpleGet < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a GET request to "/simple_get"' do
    @response = Requests.get("/simple_get")
  end

  step 'I make a GET request to "/simple_get_with_body"' do
    @response = Requests.get("/simple_get_with_body")
  end

  step 'my response should have a body with the text "Hello world"' do
    expect(@response.body).to eq "Hello world"
  end
end
