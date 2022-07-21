require "./features/steps/shared.rb"

class Spinach::Features::SimplePost < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a POST with a body to "/echo_body"' do
    @response = Requests.post("/echo_body", "some body")
  end

  step 'my response body should equal the body of my request' do
    expect(@response.body).to eq "some body"
  end
end
