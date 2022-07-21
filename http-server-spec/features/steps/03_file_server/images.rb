require "./features/steps/shared.rb"

class Spinach::Features::Images < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a GET request to \'/kitteh.jpg\'' do
    @response = Requests.get("/kitteh.jpg", { "Accept": "image/jpg" })
  end

  step 'I make a GET request to \'/doggo.png\'' do
    @response = Requests.get("/doggo.png", { "Accept": "image/png" })
  end

  step 'I make a GET request to \'/kisses.gif\'' do
    @response = Requests.get("/kisses.gif", { "Accept": "image/gif" })
  end

  step 'my response should return a JPEG image' do
    expect(@response.content_type).to eq "image/jpeg"
  end

  step 'my response should return a PNG image' do
    expect(@response.content_type).to eq "image/png"
  end

  step 'my response should return a GIF' do
    expect(@response.content_type).to eq "image/gif"
  end
end
